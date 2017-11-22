from errorPrinter import eprint
from evaluator import f
import sys

#In this method we calculate if the given point is a root
def puntoFijo(Xm,tolerancia,iteraciones):
    if(iteraciones<0 or tolerancia<0):
        eprint("el numero de iteraciones y tolerancia deben de ser positivos")
    else:
        print "-----------||------------||-------------||--------------"
        print "  X              G(x)          f(x)            Error  "
        FXm = f(Xm,funcion)
        print Xm ,"  |                |  " ,  FXm,"  |              |   "
        cont=0
        error=tolerancia+1

        #If the stop parameters haven't been broken here we can calculate where is the root
        while(FXm!=0 and cont<=iteraciones and error>tolerancia):
            GXm=f(Xm,funcionG)
            FXm=f(Xm,funcion)
            error=(GXm-Xm)/GXm
            Xm=GXm
            cont+=1
            print Xm ,"  |   ",  GXm,"  |  " ,  FXm,"  |   ",  error,"  |   "

        #Here we show the root or the aproximation if the given point's not a root
        if (FXm==0):
            print (Xm, "es una raiz")
        elif(error<=tolerancia):
            print("la raiz obtenida con un grado de tolerancia",tolerancia," de error es",Xm)
        else:
            eprint("El calculo excedio el numero de iteraciones esperadas", iteraciones)

if len(sys.argv)==6:
    funcion = sys.argv[1]
    funcionG = sys.argv[2]
    xm = float (sys.argv[3])
    tol = float (sys.argv[4])
    itera = int (sys.argv[5])
    puntoFijo(xm,tol,itera)

else:
    eprint ("no se pasaron los parametros suficientes para ejecutar el metodo")
