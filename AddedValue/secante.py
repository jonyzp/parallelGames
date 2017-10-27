from evaluator import f

def secanteMethod(x0,x1,tol,iter):
    y0= f(x0,funcion)
    if y0== 0:
        print "X0 is root"
    else:
        y1=f(x1,funcion)
        count=0
        error=tol+1
        Den=y1-y0
        print "-------------||------------||-------------"
        print "  Xn             f(Xn)         Error  "
        print x0,"  |   ",  y0,"  |  " ,  "  |   "
        print x1,"  |   ",  y1,"  |  " ,  "  |   "

        while error>tol and y1!=0 and Den!=0 and count<iter:
            Xaux= x1-((y1*(x1-x0))/Den)
            error= abs((Xaux-x1)/Xaux)
            x0=x1
            y0=y1
            x1=Xaux
            y1=f(x1,funcion)
            Den=y1-y0
            count+=1
        print x1,"  |   ",  y1,"  |  " ,  error,"  |   "
        if y1==0:
            print "X1 es root"
        elif error<tol:
            print "X1 is an approximate root with tolerance: ",tol
        elif Den==0:
            print "Might be a multiple root"
        else:
            print "Failed in ",iter," iterations"



funcion=input(str("Ingrese la funcion"))
secanteMethod(-1.099,-1.098,0.000000001,25)
