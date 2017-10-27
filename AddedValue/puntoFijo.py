from evaluator import f,g

def puntoFijo(Xm,iteraciones,tolerancia):
    if(iteraciones<0 or tolerancia<0):
        print("el numero de iteraciones y tolerancia deben de ser positivos")
    else:
        print "-----------||------------||-------------||--------------"
        print "  X              G(x)          f(x)            Error  "
        FXm = f(Xm)
        print Xm ,"  |                |  " ,  FXm,"  |              |   "
        cont=0
        error=tolerancia+1
        while(FXm!=0 and cont<=iteraciones and error>tolerancia):
            GXm=g(Xm)
            FXm=f(Xm)
            error=(GXm-Xm)/GXm
            Xm=GXm
            cont+=1
            print Xm ,"  |   ",  GXm,"  |  " ,  FXm,"  |   ",  error,"  |   "

        if (FXm==0):
            print (Xm, "es una raiz")
        elif(error<=tolerancia):
            print("la raiz obtenida con un grado de tolerancia",tolerancia," de error es",Xm)
        else:
            print("El calculo excedio el numero de iteraciones esperadas", iteraciones)


puntoFijo(-0.8,30,0.0000000001)
