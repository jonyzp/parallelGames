import numpy as np
import random as r
m = int (input('Valor de m:'))
n = int (input('Valor de n:'))

matrix =np.empty((m, n)) #empty sirve para crear valores aleatorios
vector = np.empty((m))
for i in range (m):
    for j in range(n):
        matrix[i][j]=r.randint(1,15)  #r.randint esto me realiza de una matriz de forma aleatoria del 4 al 10
    vector[i]= r.randint(1,15)
x=np.zeros((m))

print(matrix)

for k in range (m):
	for r in range(k+1,m):
		factor=(matrix[r,k]/matrix[k,k])
		vector[r]=vector[r]-(factor*vector[k])
		for c in range(0,n):
			matrix[r,c]=matrix[r,c]-(factor*matrix[k,c])

#sustitución hacia atrás
x[m-1]=vector[m-1]/matrix[m-1,m-1]
print (x[m-1])
for r in range(m-2,-1,-1):
	suma = 0
	for c in range(0,n):
		suma=suma+matrix[r,c]*x[c]
	x[r]=(vector[r]-suma)/matrix[r,r]
print ('Resultado matriz')
print(matrix)
print ('Resultado del vector')
print(vector)
print ('Resultados: ')
print(x)
