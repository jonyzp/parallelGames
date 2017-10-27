from evaluator import f

def biseccion(Xinferior, Xsuperior, tolerancia, iteraciones):

    if(tolerancia <0 or iteraciones < 0) :
		print("La tolerancia o las iteraciones no pueden ser menores que cero")
    else:
        funcionXinferior = f(Xinferior,funcion)
        funcionXsuperior = f(Xsuperior,funcion)
        if (funcionXinferior == 0) :
            print(Xinferior," es raiz.")
        elif(funcionXsuperior == 0):
            print(Xsuperior," es raiz.")
        elif(funcionXinferior*funcionXsuperior < 0):
            xm = (Xinferior+Xsuperior)/2
            funcionXm = f(xm,funcion)
            contador = 1
            errorAbs = tolerancia+1

            print "-------------||------------||-------------||--------------||------------"
            print "  Xinferior     Xsuperior       Xmedio       f(Xmedio)        Error  "
            print Xinferior ,"  |   ",  Xsuperior,"  |  " ,  xm,"  |   ",    funcionXm,"  |   ",   errorAbs,"  |   "
            while(errorAbs > tolerancia and funcionXm !=0 and contador <= iteraciones) :
                if(funcionXinferior * funcionXm < 0) :
                    Xsuperior = xm
                    funcionXsuperior = funcionXm
                else:
                    Xinferior = xm
                    funcionXinferior = funcionXm
                xaux = xm
                xm = (Xinferior + Xsuperior)/2
                funcionXm = f(xm,funcion)
                errorAbs = abs(xm - xaux)
                contador = contador + 1
                print Xinferior ,"  |   ",  Xsuperior,"  |  " ,  xm,"  |   ",    funcionXm,"  |   ",   errorAbs,"  |   "

            if(funcionXm == 0):
                print ("Xm es raiz.","%.50f"%xm)
            elif(errorAbs < tolerancia):
                print (xm, "es aproximacion a una raiz con tolerancia de:", tolerancia)
            else:
                print ("Fracaso en iteraciones ", iteraciones)
        else:
            print ("Intervalo inadecuado")


funcion=input(str("Ingrese la funcion"))
biseccion(-1.1,-1,0.00001,20)
