from evaluator import f,fd

def newton(x0,tol,iteration):
    if(iteration <0 or tol<=0):
        print("the iterations and tolerance must be positive")
    else:

        fx = f(x0)
        dfx = fd(x0)
        cont = 0
        error = tol + 1
        print "-----------||------------||-------------||--------------"
        print "  X              f(x)          f'(x)            Error  "
        print x0 ,"  |   ",  fx,"  |  " ,  dfx,"  |   ",    error,"  |   "
        while(error > tol and fx != 0 and dfx != 0 and cont <iteration):
            x1 = x0 - fx/dfx
            fx = f(x1)
            dfx = fd(x1)
            error = abs(x1 - x0)
            x0 = x1
            cont=cont+1
            print x0 ,"  |   ",  fx,"  |  " ,  dfx,"  |   ",    error,"  |   "
        if(fx == 0):
            print(x0 , "is root")
        elif(error < tol):
            print(x1 , " is an aproximation to a root with tolerance " , tol)
        elif(dfx == 0):
            print(x1 , " maybe a multiple root")
        else:
            print("failed in the ", iteration , " iteration")


newton(-1.1,0.000000000001,20)
