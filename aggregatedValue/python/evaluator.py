#Here we import de library math to do de evaluator
import math
#Here we pass the function and the evaluator return a result
def f(num,s):
    funcs = vars(math)
    libres = dict(x=num)
    a=eval(s, funcs, libres)
    return float("%.10f"%a)
