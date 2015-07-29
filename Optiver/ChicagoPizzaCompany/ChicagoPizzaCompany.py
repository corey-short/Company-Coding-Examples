__author__ = 'Corey Short'
__date__ = '4/3/15'


import sys
print sys.path
import os
from collections import deque
import csv
import json
import re

# References
# http://stackoverflow.com/questions/1744989/read-from-file-or-stdin
# https://docs.python.org/2/library/collections.html#collections.deque
#
# Constraints:
# We only accept orders for one pizza at a time.
# A pizza must be baked before it can be delivered and deep dish pizzas take longer to bake than thin crust.
# Ovens can only bake one pizza at a time.
#
# Assumptions:
# We make perfect pizzas every time.
# Therefore we do not handle the case of pizzas burning in the oven and removing them from the queue.


class ChicagoPizzaCompany:
    # The Chicago Pizza Company makes two types of pizzas: deep dish deluxe and thin crust cheese.
    # A program that, for a given stream of pizza orders, determines the time at which each order leaves
    # the Chicago Pizza Company as well as the total time it took to prepare that order.
    def __init__(self):
        self.queue = deque()
        self.DEEP_DISH_DELUXE = 0
        self.THIN_CRUST_CHEESE = 1
        self.numOvens = 3                           # We have three ovens for baking pizzas.
        self.ordersHash = {}
        self.lowestWaitHash = {}                    # Need to save to add wait time for soonest available oven
        #self.lowestDepartureTime = float("inf")

    # Get pizza type from our input
    def get_pizza_type(self, type_):
        if type_ == '0':
            return self.DEEP_DISH_DELUXE
        else:
            return self.THIN_CRUST_CHEESE

    @staticmethod
    def insert_nth(deque, n, x):
        deque.rotate(-n)
        deque.appendleft(x)
        deque.rotate(n)

    @staticmethod
    def calculate_wait_time(bake_pizza, time):
        return bake_pizza[0], bake_pizza[1] + time, bake_pizza[2] + time

    # Read and parse our input
    def read_input(self):
        # TODO: parse stdin & read arbitrary file input
        with open('input1.csv', 'r') as input_file:
            # This is hacky
            count_reader = csv.reader(input_file)
            total_rows = 0
            for _ in count_reader:
                total_rows += 1

            input_file.seek(0)      # Move input_file back to the beginning

            reader = csv.reader(input_file)
            num_orders = 0
            # TODO: Generate output file string based on input_file name
            output_file = open('output_file.txt', 'w')
            output = csv.writer(output_file)
            for row in reader:
                order_time = row[0]
                if order_time not in self.ordersHash:                # Store order_time to handle multiple orders arriving at the same time
                    self.ordersHash[order_time] = 1
                else:
                    self.ordersHash[order_time] = self.ordersHash.get(order_time) + 1

                pizza_order = PizzaOrder(order_time, row[1], self.get_pizza_type(row[2]))
                bake_pizza = BakePizza(pizza_order).bake()
                departure_time = bake_pizza[1]

                # Multiple orders same order_time
                if self.ordersHash.get(order_time) > 3:
                    bake_pizza = self.calculate_wait_time(bake_pizza, bake_pizza[2])
                    departure_time = bake_pizza[1]

                # TODO: fix this.
                #if len(self.queue) == 3:
                    #bake_pizza = BakePizza(pizza_order).bake_and_wait(self.queue[0][1])
                    #departure_time = bake_pizza[1]

                # TODO: Repeated code. I don't like this.
                if self.numOvens == 3:
                    self.queue.append(bake_pizza)
                    self.numOvens -= 1

                elif self.numOvens == 2:
                    if departure_time < self.queue[0][1]:
                        self.queue.appendleft(bake_pizza)            # Insert to head of queue to give priority for pop
                    else:
                        self.queue.append(bake_pizza)
                    self.numOvens -= 1

                elif self.numOvens == 1:
                    # TODO: Put repeat code inside fn helper
                    print
                    if departure_time < self.queue[0][1]:
                        self.queue.appendleft(bake_pizza)            # Insert to head of queue to give priority for pop
                    elif departure_time < self.queue[1][1]:
                        self.insert_nth(self.queue, 1, bake_pizza)   # Insert to middle of queue
                    else:
                        self.queue.append(bake_pizza)
                    self.numOvens -= 1

                else:
                    deliver_pizza = self.queue.popleft()             # Pop off queue for pizza delivery
                    output.writerow(deliver_pizza)
                    self.numOvens += 1
                    #print self.numOvens
                    #print self.queue[0][0]
                    #print len(self.queue)

                    if departure_time < self.queue[0][1]:
                        self.queue.appendleft(bake_pizza)            # Insert to head of queue to give priority for pop
                        self.numOvens -= 1
                    elif departure_time < self.queue[1][1]:
                        self.insert_nth(self.queue, 1, bake_pizza)   # Insert to middle of queue
                        self.numOvens -= 1
                    else:
                        self.queue.append(bake_pizza)
                        self.numOvens -= 1
                        for i in xrange(len(self.queue) - 1):            # Empty queue on append "right".
                            deliver_pizza = self.queue.popleft()     # We know the two values on the left are smaller
                            output.writerow(deliver_pizza)
                            self.numOvens += 1
                num_orders += 1
                if num_orders == total_rows:
                    for i in xrange(len(self.queue)):
                        deliver_pizza = self.queue.popleft()
                        output.writerow(deliver_pizza)
                        self.numOvens += 1

        input_file.close()
        output_file.close()


class PizzaOrder:
    # Orders consist of an order_time, order-number, and pizza_type
    def __init__(self, order_time, order_number, pizza_type):
        self.orderTime = int(order_time)
        self.orderNumber = order_number     # Order numbers strictly increase.
        self.pizzaType = pizza_type

    def get_order_time(self):
        return self.orderTime

    def get_order_num(self):
        return self.orderNumber

    def get_pizza_type(self):
        return self.pizzaType


class BakePizza:
    # Bake some pizzas!
    def __init__(self, pizza_order):
        self.BAKE_DEEP_DISH = 60            # It takes 60 seconds to bake a deep dish deluxe pizza.
        self.BAKE_THIN_CRUST = 15           # It takes 15 seconds to bake a thin crust cheese pizza.
        self.order = pizza_order

    # Get bake time for pizza_type
    def get_bake_time(self):
        if self.order.get_pizza_type() == 0:
            return self.BAKE_DEEP_DISH
        else:
            return self.BAKE_THIN_CRUST

    # Get departure time
    # Shipped time
    def get_departure_time(self):
        bake_time = self.get_bake_time()
        return self.order.get_order_time() + bake_time

    def set_departure_time(self, lowest):
        return self.get_bake_time() + lowest

    # TODO: This may be close to working
    def bake_and_wait(self, low):
        b = self.get_bake_time()
        l = low
        print "bake tm ", b
        print "low ", l
        return [self.order.get_order_num(), self.get_bake_time() + low, self.get_bake_time() + low - self.order.get_order_time()]

    # Get fulfillment time
    # The difference between departureTime and order_time
    def get_fulfillment_time(self):
        return self.get_departure_time() - self.order.get_order_time()

    # Bake if not waiting for an oven
    def bake(self):
        return [self.order.get_order_num(), self.get_departure_time(), self.get_fulfillment_time()]

    # Determine how long to wait for oven
    # def calculate_wait_time(self, lowest_depart_time):
    #    return [self.order.get_order_num(), self.get_departure_time() + lowest_depart_time, self.get_fulfillment_time() + lowest_depart_time]


if __name__ == '__main__':
    chicagoPizzaCompany = ChicagoPizzaCompany()
    chicagoPizzaCompany.read_input()

    # TODO: stdin
    '''if file_name_given:
        inf = open(file_name_given)
    else:
        inf = sys.stdin
    print inf
    '''

