import math
def f(num,s):
    funcs = vars(math)
    libres = dict(x=num)
    a=eval(s, funcs, libres)
    return float("%.10f"%a)
