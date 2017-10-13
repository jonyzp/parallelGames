#include <iostream>
#include <fstream>
#include <cstdlib>
#include <iomanip>

using namespace std;

void invermat(int n, double **a, double **ainv, double &determ);

int main() {

	system ("clear");
	ifstream label1 ("matrix.txt"); // Open the file with the matrix

	// variables Definition

	int i, j, n;
        label1 >> n;
	double **a, **ainv, determ;

	a = new double* [n], ainv = new double* [n];

	for(j=0; j<n; j++){
		a[j] = new double [n], ainv[j] = new double [n];
	}	

	// Reading the matrix from label1 that is a pointer to matrix.txt

    for(i=0; i<n; i++){
        for(j=0; j<n; j++){
            label1 >> a[i][j];
        }
	}

	cout << "Print the original matrix\n\n";
	// original matrix; a

	for(i=0; i<n; i++){
        for(j=0; j<n; j++){
                    cout <<  a[i][j] << " ";
        }
	    cout << endl;
    }

	cout << endl;
    invermat (n, a, ainv, determ);
    
	if (determ != 0) {
        cout << "Print the inverse matrix\n\n";
        // Inv matrix; ainv
	    cout.setf(ios::fixed);
	    cout.precision(6);

        for(i=0; i<n; i++){
            for(j=0; j<n; j++){
                cout << setw(10) << ainv[i][j] << " ";
            }
            cout << endl;
        }
    }else cout << "The matrix doesn't have inverse\n\n";
    
    delete a;
	return 0;
}

void invermat(int n, double **a, double **ainv, double &determ) {
    // Algorithm gaussian elimination

	int i, j, k;
	double factor;
	double **L, *D, *X;
	
	X = new double [n]; D = new double [n];
	L = new double* [n];
	
	for (j = 0; j < n; j++) L[j] = new double [n];

	for (k = 0; k < n - 1; k++) {
		for (i = k+1; i < n;  i++) {
			factor = a[i][k]/a[k][k]; 
			for (j = k+1; j < n + 1; j++) {
				a[i][j] = a[i][j] - factor * a[k][j];
            }
		}
	}

    //determinant
    determ = 1.;

	for (i = 0; i < n; i++) {
		determ = determ * a[i][i];
	}


    if (determ != 0) {
    // code to get the matrices L(lower) and U(higher) from LU descompotition
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (i > j) {
                    L[i][j] = a[i][j]/a[j][j];
                    a[i][j] = 0;
                }
            }
        }

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                L[j][j] = 1;
            }
        }

        // function to get the inverse n

        for (k = 0; k < n; k++) {

            // code to initialice L[i][n] to be use with the L matrix
                for (i = 0; i < n; i++) {
                    if (i == k) L[i][n] = 1;
                    else  L[i][n] = 0;
                }


            // This function make the progresive sustitution with the L matrix and L[i][n]
            double sum;
            D[0] = L[0][n];
            for (i = 1; i < n; i++) {
                sum = 0;
                for (j = 0; j < i; j++) {
                    sum = sum + L[i][j]*D[j];
                }
                D[i] = L[i][n] - sum;
            }

            //This function asign the D[i] (L solution) to be use with the U matrix
            for (i = 0; i < n; i++) {
                a[i][n] = D[i];
            }

            // This code make de regresive sustitution
            X[n-1] = a[n-1][n]/a[n-1][n-1];
            
            //Determinate the other roots(solutions) 
            for (i = n - 2; i > -1; i--) {
                sum = 0;
                for (j = i+1; j < n; j++) {
                    sum = sum + a[i][j]*X[j];
                }
                X[i] = (a[i][n] - sum)/a[i][i];
            }

            // asing the element of the inverse matrix
            for (i = 0; i < n; i++) {
                ainv[i][k] = X[i];
            }
        }  
    } 

    delete L, D, X;
}
