import math
def f(num):
    funcs = vars(math)
    libres = dict(x=num)
    #(EXP(-3*G6))-(9*(EXP(-2*G6)))+(27*EXP(-G6))-27
    #s = "(e**(-3*x))-(9*(e**(-2*x)))+(27*e**(-x))-27"
    #""EXP(-2*G5-5)-G5^2+2*G5""
    #
    s= "e**(-3*x)-(9*e**(-2*x))+(27*e**(-x))-27"
    a=eval(s, funcs, libres)
    #print "F()=","%.50f"%a
    return float("%.10f"%a)

def g(num):
    funcs = vars(math)
    libres = dict(x=num)
    #-e^(-2x-5)+x^(2)-x
    s = "(-e**((-2*x)-5))+(x**2)-x"
    a=eval(s, funcs, libres)
    #print "F()=","%.50f"%a
    return float("%.10f"%a)

def fd(num):
    funcs = vars(math)
    libres = dict(x=num)
    #-e^(-2x-5)+x^(2)-x
    s = "-27*e**(-x)+(18*e**(-2*x))-(3*e**(-3*x))"
    a=eval(s, funcs, libres)
    #print "F()=","%.50f"%a
    return float("%.10f"%a)


def fdd(num):
    funcs = vars(math)
    libres = dict(x=num)
    #-e^(-2x-5)+x^(2)-x
    s = "27*e**(-x)-(36*e**(-2*x))+(9*e**(-3*x))"
    a=eval(s, funcs, libres)
    #print "F()=","%.50f"%a
    return float("%.10f"%a)
