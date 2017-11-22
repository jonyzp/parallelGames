from errorPrinter import eprint
from evaluator import f
import sys

#With this method we want to find a good interval with posible root
def incrementalSearch(x0, delta, iter):
    if(delta==0):
        eprint("Delta no puede ser cero")
    if(iter<=0):
        eprint("Las iteraciones no pueden cer menores o iguales a cero")
    fx0 = f(x0,funcion)
    if(fx0 == 0):
        print(x0 + " es raiz")
    else:
        x1 = x0 + delta
        contador = 1
        fx1 = f(x1,funcion)
        print "-----------||------------"
        print "  X              f(x)    "
        print x0 ,"  |   ",  fx0,"  |  "
        print x1 ,"  |   ",  fx1,"  |  "

        #If the stop parameters haven't been broken here we can calculate the interval
        while(fx0 * fx1 > 0 and contador <= iter):
            x0 = x1
            fx0 = fx1
            x1 = x0 + delta
            fx1 = f(x1,funcion)
            contador+=1
            print x1 ,"  |   ",  fx1,"  |  "

        #Here we can show the interval that has a posible root
        if(fx1 == 0):
            print(x1 , " es la raiz")
        elif(fx0 * fx1 < 0):
            print("Hay raiz entre " , x0 , " y " , x1)

        else:
            eprint("Fracaso en " , iter , " numero iteraciones")


if len(sys.argv)==5:
    funcion = sys.argv[1]
    x0 = float (sys.argv[2])
    delta = float (sys.argv[3])
    itera = int (sys.argv[4])
    incrementalSearch(x0,delta,itera)
else:
    eprint ("no se pasaron los parametros suficientes para ejecutar el metodo")
