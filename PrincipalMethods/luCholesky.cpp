#include <bits/stdc++.h>
#include <math.h>
#include <time.h>
using namespace std;

double **L, **U, *B, *Z, *X, **A;

//With this method we initialize two matrixes (L and U) whit a specific size
void fillMatrix( int size){
    for (int i = 0; i <size; ++i){
        for (int j =0 ; j<size; ++j){
            L[i][j] =0;
        }
    }
    for (int i = 0; i <size; ++i){
        for (int j =0 ; j<size; ++j){
            U[i][j]=0 ;
        }
    }
}

//Here we read the matrix and we get the value of U and L matrixes
void readMatrix( int size,string origin){
    B = new double[size];
    U = new double *[size];
    A= new double *[size];
    for (int i = 0; i < size; ++i){
        U[i] = new double [size];
    }

    for (int i = 0; i < size; ++i){
        A[i] = new double [size];
    }

    L = new double*[size];

    for (int i = 0; i < size; ++i){
        L[i] = new double[size];
    }

    fillMatrix(size);
    ifstream read;
    read.open(origin.c_str());
    read >> B[0];
    for (int i = 0; i < size; ++i){
        for (int j = 0; j < size; ++j){
            read >> A[i][j];
        }
        read >> B[i];
    }

    read.close();
}

//Here we try to solve the matrix with the method cholesky
void cholesky(int n){
    double suma1,suma2,suma3;

    for(int k=0;k<n;++k){
        suma1=0;
        for(int m=0;m<k;++m){
            suma1+=L[k][m]*U[m][k];
        }
        L[k][k]=sqrt(A[k][k]-suma1);
        U[k][k]=L[k][k];

        for(int i=k;i<n;++i){
            suma2=0;
            for(int p=0;p<k;++p){
                suma2+=L[i][p]*U[p][k];
            }
            L[i][k]=(A[i][k]-suma2)/(double)U[k][k];
        }

        for(int j=k+1;j<n;++j){
            suma3=0;
            for(int h=0;h<k;++h){
                suma3+=L[k][h]*U[h][j];
            }
            U[k][j]=(A[k][j]-suma3)/(double)L[k][k];

        }

    }
}

//Here we find the value of the Z's and then with this we find the value of the unknowns
void progressiveC(int n){
	Z = new double[n];
	for(int i=0; i<n; i++){
		double sum = 0;
		for(int p=0; p<i; ++p){
			sum += L[i][p]*Z[p];
		}
		Z[i]=(B[i]-sum)/L[i][i];
	}
}

//Here we find the value of the unknowns
void regressiveC(int n){
	X = new double[n];
	for(int i=n-1; i>=0; --i){
		double sum = 0;
		for(int p=i+1; p<n; ++p){
			sum += U[i][p]*X[p];
		}
		X[i]=(Z[i]-sum)/U[i][i];
	}
}

//Here we put the solution in a file
void printSolution(string solutionFile, int size){
    ofstream write;
    write.open(solutionFile.c_str(),ios::trunc);
    for (int i = 0; i <size; ++i){
        write << X[i] << endl;
    }


    write.close();
}

//This is the main method that ejecuted all the methods of the class
int main(){
	string originalFile,readingName,writingName,solutionFile;
    long long matrixSize;
    clock_t start, end;
    originalFile="matrix.txt";
    solutionFile="solution.txt";
    ifstream f("matrix.txt");
    f >> matrixSize;


    readMatrix(matrixSize,originalFile);
    start = clock();
    cholesky(matrixSize);
    progressiveC(matrixSize);
    regressiveC(matrixSize);
    end = clock();
    printf("The time was: %.30g\n", (double)( (end - start) / 1000.0));
    printSolution(solutionFile, matrixSize);
	return 0;
}
