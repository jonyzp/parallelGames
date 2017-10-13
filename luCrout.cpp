#include <bits/stdc++.h>
using namespace std;

double **L, **U, *B, *Z, *X;
//, **LU,*y;

int initialMatrixProcessing(string origin){
    ifstream read;
    stringstream ss;
    string firstLine;
    int cont;
    long long var;

    read.open(origin.c_str());

    getline(read, firstLine);
    ss.str(firstLine);
    
    cont = 0;
    while (ss >> var) cont++;
    read.close();
    return cont-1;
}

void readMatrix( int size,string origin){
	B = new double[size];
    U = new double *[size];
    
    for (int i = 0; i < size; ++i){
        U[i] = new double [size];
    }

    for (int i = 0; i < size; ++i){
        cout<<U[i];
    }

    L = new double*[size];
    
    for (int i = 0; i < size; ++i){
		L[i] = new double[size];
    }

    
    for (int i = 0; i < size; ++i){
        cout<<L[i];
    }
    
    ifstream read;
    read.open(origin.c_str());
    
    for (int i = 0; i < size; ++i){
        for (int j = 0; j < size; ++j){
            read >> U[i][j];
        }
        read >> B[i];
    }
    cout << B;

    read.close();
}

void gaussianElimination(int n){
	double multiplier;
	for(int k=0; k<n; ++k){
		L[k][k]=1;
		for(int i=k+1; i<n; ++i){
			multiplier = U[i][k]/U[k][k];
			L[i][k]=multiplier;
			for(int j=k; j<n; ++j){
				U[i][j] = U[i][j] - multiplier*U[k][j];
			}
		}
	}
}
/*
void solveCrout(int d){
    //double y[d];
    y = new double[d];
    for(int i=0;i<d;++i){
       double sum=0.;
       for(int k=0;k<i;++k){
           sum+=LU[i*d+k]*y[k];
       }
       y[i]=(B[i]-sum)/LU[i*d+i];
    }
    for(int i=d-1;i>=0;--i){
       double sum=0.;
       for(int k=i+1;k<d;++k){
           sum+=LU[i*d+k]*X[k];
       }
       X[i]=(y[i]-sum); // not dividing by diagonals
    }
}
*/
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

void printSolution(string solutionFile, int size){
    ofstream write;
    write.open(solutionFile.c_str(),ios::trunc);
    for (int i = 0; i <size; ++i){
        write << X[i] << endl;
    }
    write.close();
}

int main(){
	string originalFile,readingName,writingName,solutionFile;
    long long matrixSize;
    //cout << "where is the original matrix at?" << endl;
    //cin >> originalFile;
    originalFile="matrix.txt";

    //cout << "where do you want the solution to be?" << endl;
    //cin >> solutionFile;
    solutionFile="solucion.txt";
    matrixSize = initialMatrixProcessing(originalFile);    
    /*
    ifstream f("matrix.txt");
    f >> matrixSize; 
    for (int i = 1; i <= matrixSize; i++){
        for (int j = 1; j <= matrixSize; j++){
            f >> LU[i][j];
        }
        f >> LU[i][matrixSize+1];
    }  
    //cout << matrixSize;
    //f.close();*/
    readMatrix(matrixSize,originalFile);
    gaussianElimination(matrixSize);
    //solveCrout(matrixSize);
    progressiveC(matrixSize);
    regressiveC(matrixSize);
    printSolution(solutionFile, matrixSize);
	return 0;
}
