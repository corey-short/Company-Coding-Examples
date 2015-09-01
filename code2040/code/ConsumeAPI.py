__author__ = 'Corey Short'
__date__ = '08/1/15'

import json
import requests
import ast


class ConsumeAPI:
    # This is still not finished.
    def __init__(self):
        self.headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
        self.token = None

    def register(self):
        # Send registration request
        url = 'http://challenge.code2040.org/api/register'
        # A JSON dict with the keys email and github sent in the body of our request
        data = {'email': 'cshort012@gmail.com', 'github': 'http://github.com/cshort12/code2040'}
        print data
        self.token = self.post(url, data)
        print self.token

    def post(self, url, data):
        # Should be using try except. what if the response is bad :(
        response = requests.post(url, data=json.dumps(data), headers=self.headers)
        response = response.text.encode('utf-8')
        return response

    def parse_response(self, response):
        # This is Hacky. Should use regex
        parsed_response = response.split(":")[1].split("}")[0].replace('"', '')
        return parsed_response

    def validate_response(self, response):
        # Validate a response
        parsed_response = self.parse_response(response)
        if parsed_response == 'PASS':
            return True
        return False

    def reverse_string(self):
        # Reverse a string. The python way!
        url = 'http://challenge.code2040.org/api/getstring'
        data = {'token': self.token}
        response = self.post(url, data)
        parsed_response = self.parse_response(response)
        reversed_string = parsed_response[::-1]

        data.update({'string': reversed_string})
        url = 'http://challenge.code2040.org/api/validatestring'
        response = self.post(url, data)
        self.validate_response(response)

    def needle_n_haystack(self):
        url = 'http://challenge.code2040.org/api/haystack'
        data = {'token': self.token}
        response = self.post(url, data)
        index_of_needle_in_haystack = self.get_index_of_needle_n_haystack_helper(response)

        data.update({'needle': index_of_needle_in_haystack})
        url = 'http://challenge.code2040.org/api/validateneedle'
        response = self.post(url, data)
        self.validate_response(response)

    def get_index_of_needle_n_haystack_helper(self, response):
        # Convert unicode to a python dictionary. Let's try something different.
        """
        Safely evaluate an expression node or a string containing a Python expression.
        The string or node provided may only consist of the following Python literal structures:
        strings, numbers, tuples, lists, dicts, booleans, and None.
        https://stackoverflow.com/questions/14950260/convert-unicode-string-dictionary-into-dictionary-in-python
        """
        response_to_dict = ast.literal_eval(response)
        needle_n_haystack_dict = response_to_dict.values()[0]
        needle = ''
        haystack = []
        # position = None
        for key in needle_n_haystack_dict:
            if key == 'needle':
                needle = needle_n_haystack_dict.get(key)
            if key == 'haystack':
                haystack = needle_n_haystack_dict.get(key)
        # Should check if haystack is None
        for i in haystack:
            if i == needle:
                position = haystack.index(i)
                return position
