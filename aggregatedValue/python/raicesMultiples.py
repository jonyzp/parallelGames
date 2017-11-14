from errorPrinter import eprint
from evaluator import f
import sys
def raicesMultiples(Xo,tol,nIter):
    fx=f(Xo,funcion)
    dfx=f(Xo,derivada)
    ddfx=f(Xo,segundaderivada)
    count=0
    error=tol+1
    den=(dfx*dfx)-(fx*ddfx)
    print "-----------||------------||-------------||-------------||--------------"
    print "  X              f(x)          f'(x)        f''(x)            Error  "
    print Xo ,"  |   ",  fx,"  |  " ,  dfx,"  |   ",    error,"  |   "
    while fx!=0 and error>tol and den !=0 and count<nIter:
        Xi=Xo-(fx*dfx)/den
        error=abs(Xi-Xo)
        Xo=Xi
        fx=f(Xo,funcion)
        dfx=f(Xo,derivada)
        ddfx=f(Xo,segundaderivada)
        den=(dfx *dfx) -(fx * ddfx)
        count+=1
        print Xo ,"  |   ",  fx,"  |  " ,  dfx,"  |   ",    error,"  |   "

    if (fx == 0):
            print Xi, " is a root"
    elif (error <= tol) :
            print Xi, " is an approximation with ",tol," tolerance"
    elif (dfx == 0) :
            print "there are possible multiple roots at ", Xi
    else:
            eprint ("failure after N iterations")

if len(sys.argv)==7:
    funcion = sys.argv[1]
    derivada = sys.argv[2]
    segundaderivada = sys.argv[3]
    x0 = float (sys.argv[4])
    tol = float (sys.argv[5])
    itera = int (sys.argv[6])
    raicesMultiples(x0,tol,itera)
else:
    eprint ("no se pasaron los parametros suficientes para ejecutar el metodo")


