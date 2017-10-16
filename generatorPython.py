import random

file= open("matrix.txt",'w')

def randomGenerator(size,rango):
    suma = 0
    sol=[]
    for i in range (size):
            randVal =random.randrange(rango)+1
            suma+= randVal
            sol.append(randVal)
    sol.append(suma)
    return sol

def matrixGenerator(size,rango):
    for i in range(size):
        actual = randomGenerator(size,rango)
        bVal=0
        line=""
        for j in range(size):
            if i==j:
                printVal=actual[len(actual)-1]
            else:
                printVal = actual[j]
            line+=str(printVal)+" "

            bVal += (j+1) * printVal
        line=line + str(bVal)
        #print line
        file.write(line+"\n")

def main():
    rango=9
    size=10
    file.write(str(size)+"\n")
    matrixGenerator(size,rango)

main()
