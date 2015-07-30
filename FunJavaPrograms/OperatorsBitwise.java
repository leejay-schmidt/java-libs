public class OperatorsBitwise {
  public static int add(int x, int y) {
    if(x==0||y==0) return x==0?y:x;
    int kmask=1, sum=0, c_in=0;
    if(y>x) {
      int tmp = x;
      x = y;
      y = x;
    }
    int max = x; 
    while(max > 0) {
      int a = x&kmask, b = y&kmask;
      sum |= ((a^b)^c_in);
      int c_out = (a&b)|(c_in&(a^b));
      c_in = c_out<<1;
      max>>=1; 
      kmask<<=1;
    }
    return sum | c_in;
  }
  public static int sub(int x, int y) {
    if (x==0||y==0) return x==0?-(y):x;
    int kmask=1, diff=0, bor_in=0;
    boolean neg = x>y?false:true;
    if (neg) {
      int i = x;
      x = y;
      y = i;
    }
    int max = x;
    while(max > 0) {
      int a=x&kmask, b=y&kmask;
      diff |= ((a^b)^bor_in);
      int bor_out = ((~a)&(b|bor_in))|(a&b&bor_in);
      bor_in = bor_out<<1;
      kmask<<=1;
      max>>=1;
    }
    return neg?-(diff|bor_in):(diff|bor_in);
  }
  public static int div(int num, int den) {
    int result = 0;
    while(num >= den) {
      int pow = 1;
      while((den<<pow) >= (den<<(pow-1)) && (den<<pow) <= num) {
        ++pow;
      }

      result += 1<<(pow-1);
      num = sub(num, (den<<(pow-1)));
    }
    return result;
  }
  /*public static int div(int num, int den) {
    if(den==0) return ~0;
    if(num==0 || (den>num)) return 0;
    while((den&1)==0) { //fast reduction by 2
      num>>=1;
      den>>=1;
    }
    if(den==1) return num;
    int quot = 0;
    while((num=sub(num,den))>0) {
      quot = add(quot,1);
    }
    return quot;
  }*/
  public static int mult(int x, int y) {
    int sum = 0;
    if(x==0||y==0) return 0;
    while(x > 0) {
      if((x&1)==1) {
        sum = add(sum, y);
      }
      x >>= 1;
      y <<= 1;
    }
    return sum;
  }
  /*public static int mult(int x, int y) {
    if(x==0||y==0) return 0;
    if((x&1)==0&&(y&1)!=0) {
      int i = x;
      x = y;
      y = i;
    }
    while((y&1)==0) {
      x<<=1;
      y>>=1;
    }
    if(y==1) return x;
    int m = x;
    while((y=sub(y,1))>0) {
      m=add(m,x);
    }
    return m;
  }*/
  public static int remainder(int num, int den) {
    while(num >= den) {
      int pow = 1;
      while((den<<pow) >= (den<<(pow-1)) && (den<<pow) <= num) {
        ++pow;
      }
      num = sub(num, (den<<(pow-1)));
    }
    return num;
  }
  /*public static int remainder(int num, int den) {
    if((num==0)||(den==0)) return 0;
    if(den>num) return num;
    if(den==1) return 0;
    int tmpden = den, tmpnum = num;
    //do bit shift modulo if possible, otherwise revert to slow method
    while((tmpden&1)==0&&(tmpnum&1)==0) {
      tmpnum>>=1;
      tmpden>>=1;
    }
    if(tmpden==1) return num;
    int prev = num;
    while((num=sub(num,den))>0)
      prev = num;
    return prev;
  }*/

  public static void main(String[] args) {
    int z = add(7832, 6680);
    System.out.println(z);
    int q = sub(7654, 15643);
    System.out.println(q);
    int d = div(256,34);
    System.out.println(d);
    int m = mult(46,12);
    System.out.println(m);
    int k = remainder(256,34);
    System.out.println(k);
    k = remainder(287, 32);
    System.out.println(k);
  }
}
