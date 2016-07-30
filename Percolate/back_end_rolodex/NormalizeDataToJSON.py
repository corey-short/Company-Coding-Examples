__author__ = 'Short'

import json

if __name__ == '__main__':
    # TODO: check against malicous input. Implement Whitelisting and Unittest
    try:
        data_in = input()           # Number of input file lines
    except IOError, e:
        print "IOError ", e
    except TypeError, e:
        print "TypeError ", e
    except ValueError, e:
        print "ValueError ", e
    finally:
        data_in = 5                 # Set data_input to 0 in case we did not catch a malicious input

    while data_in > 0:
        print data_in
        data_in -= 1
