from evaluator import f

def incrementalSearch(x0, delta, iter):
    if(delta==0):
        print("Delta no puede ser cero")
    if(iter<=0):
        print("Las iteraciones no pueden cer menores o iguales a cero")
    fx0 = f(x0)
    if(fx0 == 0):
        print(x0 + " es raiz")
    else:
        x1 = x0 + delta
        contador = 1
        fx1 = f(x1)
        print "-----------||------------"
        print "  X              f(x)    "
        print x0 ,"  |   ",  fx0,"  |  "
        print x1 ,"  |   ",  fx1,"  |  "

        while(fx0 * fx1 > 0 and contador <= iter):
            x0 = x1
            fx0 = fx1
            x1 = x0 + delta
            fx1 = f(x1)
            contador+=1
            print x1 ,"  |   ",  fx1,"  |  "

        if(fx1 == 0):
            print(x0 , " es la raiz")
        elif(fx0 * fx1 < 0):
            print("Hay raiz entre " , x0 , " y " , x1)

        else:
            print("Fracaso en " , iter , " numero iteraciones")


incrementalSearch(-10,1,20)
