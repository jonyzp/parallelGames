import numpy as np
print "The matrix could be like this typed [1,2,3],[3,4,5],[5,6,7]"
print "--------------------------"
m=np.array(input("Type a matrix here"))
print "--------------------------"
print "Here is the matrix",m
print "--------------------------"
print "Here is the determinant of the matrix"
print np.linalg.det(m)
