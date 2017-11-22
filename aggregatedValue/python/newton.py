from evaluator import f
import sys
from errorPrinter import eprint

#In this method we calculate if the given point is a root
def newton(x0,tol,iteration):
    if(iteration <0 or tol<=0):
        eprint("the iterations and tolerance must be positive")
    else:

        fx = f(x0,funcion)
        dfx = f(x0,derivada)
        cont = 0
        error = tol + 1
        print "-----------||------------||-------------||--------------"
        print "  X              f(x)          f'(x)            Error  "
        print x0 ,"  |   ",  fx,"  |  " ,  dfx,"  |   ",    error,"  |   "

        #If the stop parameters haven't been broken here we can calculate where is the root
        while(error > tol and fx != 0 and dfx != 0 and cont <iteration):
            x1 = x0 - fx/dfx
            fx = f(x1,funcion)
            dfx = f(x1,derivada)
            error = abs(x1 - x0)
            x0 = x1
            cont=cont+1
            print x0 ,"  |   ",  fx,"  |  " ,  dfx,"  |   ",    error,"  |   "

        #Here we show the root or the aproximation if the given point's not a root
        if(fx == 0):
            print(x0 , "is root")
        elif(error < tol):
            print(x0 , " is an aproximation to a root with tolerance " , tol)
        elif(dfx == 0):
            print(x0 , " maybe a multiple root")
        else:
            eprint("failed in the ", iteration , " iteration")


if len(sys.argv)==6:
    funcion = sys.argv[1]
    derivada = sys.argv[2]
    x0 = float (sys.argv[3])
    tol = float (sys.argv[4])
    itera = int (sys.argv[5])
    newton(x0,tol,itera)
else:
    eprint("no se pasaron los parametros suficientes para ejecutar el metodo")
