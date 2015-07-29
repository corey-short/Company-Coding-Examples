####Chicago Pizza Company

The Chicago Pizza Company makes two types of pizzas: deep dish deluxe and thin crust cheese. We only accept orders for one pizza at a time.
A pizza must be baked before it can be delivered and deep dish pizzas take longer to bake than thin crust. Your goal is to write a program which, 
for a given stream of pizza orders, determines the time at which each order leaves the Chicago Pizza Company as well as the total time it took to 
prepare that order (the difference between the time the pizza order was received and the time it was shipped).

####Pizza Details

1.Orders
    o Consist of an order time, order number, and pizza type.
    o “Pizza Type” is an integer: 0 for deep dish deluxe, 1 for thin crust cheese.
    o Order numbers strictly increase.
2. Pizza Baking:
    o We have three ovens for baking pizzas.
    o It takes 60 seconds to bake a deep dish deluxe pizza.
    o It takes 15 seconds to bake a thin crust cheese pizza.
    o Ovens can only bake one pizza at a time.
3. Order Delivery:
    o After a pizza is baked, it is delivered instantaneously by our trusty TARDIS.

####Time
1. Time starts at 0.
2. Time ticks in seconds.
3. When deciding on the next order to process, choose the lowest order number.

####Running your program
1. You may write your program in C, C++, Java, C#, or Python.
2. External libraries beyond standard system or built-in libraries may not be used. The exceptions we allow are STL, Boost, and unit testing and mocking libraries.
3. Your program should take two arguments: an input file and an output file.
4. File Format: ASCII CSV (without headers). See samples for details.
    o Input: OrderTime,OrderNumber,PizzaType
    o Output: OrderNumber,DepartureTime,FulfillmentTime
    o Note: If two pizzas finish at the same time, output the lowest order number first.
5. Sample files are provided with corresponding sample output. Output files can be used to resolve ambiguities, as they are generated from our reference implementation.
6. Be precise with your file formats. We will not fix your program to match our expected input/output. We run a simple diff in Linux and look for lack of output.
7. You do not need to detect invalid input, we will only test with valid orders and properly formatted files.
8. When you are finished, please e-mail lyndseydean@optiver.com a single zip file containing:
    o Source Files
    o Test Files
    o Build Files (e.g. make files, visual studio solution files, etc)
    o README File (See attached sample)
    o NO binary files. If your zip contains dll, exe, jar, class, or other binary files we will not receive your e-mail.

######Confidential; © Optiver 2014
