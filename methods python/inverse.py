from numpy import matrix
from numpy import linalg
import numpy as np
print "This is a program that calculates some point products, multiplications and solve a linear system equation"

A = matrix( [[1,2,3],[11,12,13],[21,22,23]])
x = matrix( [[1],[2],[3]] )
y = matrix( [[1,2,3]] )
print "---------------------------------"
print "Point product of A and the Transpose of A"
print A.T
print "---------------------------------"
print "Multiplication of A and x"
print A*x
print "---------------------------------"
print "Point product of A and the Identity"
print A.I
print "---------------------------------"
print "Solution of the equation system"
print linalg.solve(A, x)
print np.linalg.det(A)
