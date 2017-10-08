#include<iostream>
#include<cmath>
#include<iomanip>//library to be able to use the function setprecision (int)
using namespace std;
/*
M-->augmented linear system matrix
n-->number of ecuations
*/
/*
*
modify and then know if the matrix is ​​diagonally dominant
* if the matrix is ​​diagonally dominant ensures the convergence of the method
*/
bool convergence(double M[100][100],int n){
    double may;//variable to store the largest of column k
    int ind;//Index of the major --> Index of May
    bool band=true;
    double aux;
    double acum;

    for(int k=0;k<n;k++){//To traverse reduced matrix columns
        may=abs(M[k][k]);//initialize with the first element of the column         ind=k;
        //go through column k to look for the index of the major
        for(int l=k+1;l<n;l++){
            if(may<abs(M[l][k])){
                may=abs(M[l][k]);
                ind=l;
            }

        }


        //change rows
        if(k!=ind){//ensure that it is not the same row
            for(int i=0;i<n+1;i++){
                aux=M[k][i];
                M[k][i]=M[ind][i];
                M[ind][i]=aux;
            }

        }
        //verify convergence
        acum=0;
        for(int j=0;j<n;j++){
            if(k!=j){
                acum=acum+abs(M[k][j]);
            }
        }
        if(acum>abs(M[k][k])){//It's not a diagonally dominant matrix            band=false;
            break;//finish the first loop
        }


}
    return band;
}


/*
M-->modified matrix in the convergence function (matrix diagonally dominant)
V-->vector of the solution (initialized with the initial points of the variables)
n-->number of equations
tol-->tolerance to find the solution
*/
int jacobi(double M[100][100],double V[100],int n,double tol){
    double error=0;
    double acum=0;
    double VA[100];//vector solution of the previous iteration
    int iter=0;//number of iterations
    do{
        iter++;

        //traverse diagonal of diminished matrix
        for(int k=0;k<n;k++){
            acum=M[k][n];
            //traverse the row k of the diminished matrix
            for(int j=0;j<n;j++){
                if(k!=j){
                    acum=acum-VA[j]*M[k][j];
                }

            }
            V[k]=acum/M[k][k];

        }

        //error
        acum=0;
        for(int i=0;i<n;i++){
            error=V[i]-VA[i];
            acum=acum+pow(error,2);
        }
        error=sqrt(acum);

        //preparing VA for the next iteration
        for(int i=0;i<n;i++){
            VA[i]=V[i];
        }
    }while(error>tol);
    return iter;
}

int main (int argc, char *argv[]) {
    double M[100][100];
    int n;
    bool band;
    double V[100];
    double tol;
    cout<<"enter the number of equations:";
    cin>>n;
    cout<<"enter elements of the augmented matrix:"<<endl;
    for(int i=0;i<n;i++){
        cout<<"row "<<i+1<<":"<<endl;
        for(int j=0;j<n+1;j++){
            cout<<"\tcolumn "<<j+1<<":"<<endl;
            cin>>M[i][j];
        }
    }
    cout<<"increased matrix:"<<endl;
    for(int i=0;i<n;i++){
        for(int j=0;j<n+1;j++){
            cout<<M[i][j]<<"\t";
        }cout<<endl;
    }
    band=convergence(M,n);
    if(band){

        cout<<"convergence is guaranteed"<<endl;
        cout<<"diagonally dominant matrix:"<<endl;
        for(int i=0;i<n;i++){
            for(int j=0;j<n+1;j++){
                cout<<M[i][j]<<"\t";
            }cout<<endl;
        }
        if(band){
            cout<<"enter tolerance: ";
            cin>>tol;


            cout<<"-->iterations: "<<jacobi(M,V,n,tol)<<endl;
            cout<<"the solution is:"<<endl;
            for(int i=0;i<n;i++){
                cout<<"-->X"<<i+1<<"="<<setprecision(30)<<V[i]<<endl;
            }
        }



    }
    else
        cout<<"convergence is not guaranteed -> the matrix is ​​not diagonally dominant"<<endl;



    return 0; }
