// Crout uses unit diagonals for the upper triangle
void Crout(int d,double*S,double*D){
   for(int k=0;k<d;++k){
      for(int i=k;i<d;++i){
         double sum=0.;
         for(int p=0;p<k;++p)sum+=D[i*d+p]*D[p*d+k];
         D[i*d+k]=S[i*d+k]-sum; // not dividing by diagonals
      }
      for(int j=k+1;j<d;++j){
         double sum=0.;
         for(int p=0;p<k;++p)sum+=D[k*d+p]*D[p*d+j];
         D[k*d+j]=(S[k*d+j]-sum)/D[k*d+k];
      }
   }
}
void solveCrout(int d,double*LU,double*b,double*x){
   double y[d];
   for(int i=0;i<d;++i){
      double sum=0.;
      for(int k=0;k<i;++k)sum+=LU[i*d+k]*y[k];
      y[i]=(b[i]-sum)/LU[i*d+i];
   }
   for(int i=d-1;i>=0;--i){
      double sum=0.;
      for(int k=i+1;k<d;++k)sum+=LU[i*d+k]*x[k];
      x[i]=(y[i]-sum); // not dividing by diagonals
   }
}
[10:12, 10/13/2017] Marcos: // Doolittle uses unit diagonals for the lower triangle
void Doolittle(int d,double*S,double*D){
   for(int k=0;k<d;++k){
      for(int j=k;j<d;++j){
         double sum=0.;
         for(int p=0;p<k;++p)sum+=D[k*d+p]*D[p*d+j];
         D[k*d+j]=(S[k*d+j]-sum); // not dividing by diagonals
      }
      for(int i=k+1;i<d;++i){
         double sum=0.;
         for(int p=0;p<k;++p)sum+=D[i*d+p]*D[p*d+k];
         D[i*d+k]=(S[i*d+k]-sum)/D[k*d+k];
      }
   }
}
void solveDoolittle(int d,double*LU,double*b,double*x){
   double y[d];
   for(int i=0;i<d;++i){
      double sum=0.;
      for(int k=0;k<i;++k)sum+=LU[i*d+k]*y[k];
      y[i]=(b[i]-sum); // not dividing by diagonals
   }
   for(int i=d-1;i>=0;--i){
      double sum=0.;
      for(int k=i+1;k<d;++k)sum+=LU[i*d+k]*x[k];
      x[i]=(y[i]-sum)/LU[i*d+i];
   }
}
[10:12, 10/13/2017] Marcos: // Cholesky requires the matrix to be symmetric positive-definite
void Cholesky(int d,double*S,double*D){
   for(int k=0;k<d;++k){
      double sum=0.;
      for(int p=0;p<k;++p)sum+=D[k*d+p]*D[k*d+p];
      D[k*d+k]=sqrt(S[k*d+k]-sum);
      for(int i=k+1;i<d;++i){
         double sum=0.;
         for(int p=0;p<k;++p)sum+=D[i*d+p]*D[k*d+p];
         D[i*d+k]=(S[i*d+k]-sum)/D[k*d+k];
      }
   }
}
// This version could be more efficient on some architectures
// Use solveCholesky for both Cholesky decompositions
void CholeskyRow(int d,double*S,double*D){
   for(int k=0;k<d;++k){
      for(int j=0;j<d;++j){
         double sum=0.;
         for(int p=0;p<j;++p)sum+=D[k*d+p]*D[j*d+p];
         D[k*d+j]=(S[k*d+j]-sum)/D[j*d+j];
      }
      double sum=0.;
      for(int p=0;p<k;++p)sum+=D[k*d+p]*D[k*d+p];
      D[k*d+k]=sqrt(S[k*d+k]-sum);
   }
}
void solveCholesky(int d,double*LU,double*b,double*x){
   double y[d];
   for(int i=0;i<d;++i){
      double sum=0.;
      for(int k=0;k<i;++k)sum+=LU[i*d+k]*y[k];
      y[i]=(b[i]-sum)/LU[i*d+i];
   }
   for(int i=d-1;i>=0;--i){
      double sum=0.;
      for(int k=i+1;k<d;++k)sum+=LU[k*d+i]*x[k];
      x[i]=(y[i]-sum)/LU[i*d+i];
   }
}