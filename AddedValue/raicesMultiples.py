from evaluator import f,fd,fdd

def raicesMultiples(Xo,tol,nIter):
    fx=f(Xo)
    dfx=fd(Xo)
    ddfx=fdd(Xo)
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
        fx=f(Xo)
        dfx=fd(Xo)
        ddfx=fdd(Xo)
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

raicesMultiples(-1.1,0.00000000000000001,20)
