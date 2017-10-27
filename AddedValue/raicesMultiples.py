from evaluator import f

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
            print "failure after N iterations"

funcion=input(str("Ingrese la funcion"))
derivada=input(str("Ingrese la derivada de la funcion"))
segundaderivada=input(str("Ingrese la segunda derivada de la funcion"))

raicesMultiples(-1.1,0.00000000000000001,20)
