n,m = [int(i) for i in raw_input().split()]
ans = 0
# print (n, m)
for i in range(1,m+1):
    # print "\t"+str(i)
    for j in range(1,m+1):
        # print "\t\t"+str(j)
        # print (i*i+j*j)
        if (i*i+j*j)%m == 0:
            # print (i*i+j*j)
            n1 = int((n - i)/m)+1
            n2 = int((n - j)/m)+1
            print ((n-i)/m)
            print ((n-j)/m)
            # print n1
            # print n2
            ans +=  n1*n2
print(ans)