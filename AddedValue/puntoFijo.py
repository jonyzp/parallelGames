from evaluator import f

def puntoFijo(Xm,iteraciones,tolerancia):
    if(iteraciones<0 or tolerancia<0):
        print("el numero de iteraciones y tolerancia deben de ser positivos")
    else:
        print "-----------||------------||-------------||--------------"
        print "  X              G(x)          f(x)            Error  "
        FXm = f(Xm,funcion)
        print Xm ,"  |                |  " ,  FXm,"  |              |   "
        cont=0
        error=tolerancia+1
        while(FXm!=0 and cont<=iteraciones and error>tolerancia):
            GXm=f(Xm,funcionG)
            FXm=f(Xm,funcion)
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


funcion=input(str("Ingrese la funcion"))
funcionG=input(str("Ingrese la funcion G"))

puntoFijo(-0.8,30,0.0000000001)
