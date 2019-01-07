#include <bits/stdc++.h>

using namespace std;
typedef long long ll;
const int maxn = 2*100000 + 10;
ll  add[maxn<<2];
ll  arr[maxn];
int cnt[maxn];
int n, q;

void push_down(int rt) {
    if(add[rt]) {
        add[rt<<1] += add[rt];
        add[rt<<1|1] += add[rt];
        add[rt] = 0;
    }
    return ;
}

void build(int l, int r, int rt) {
    add[rt] = 0;
    if(l == r) return ;
    int m = (l + r) / 2;
    build(l, m, rt<<1);
    build(m + 1, r, rt<<1|1);
    return ;
}

void update(int L, int R, int c /*1*/, int l/*1*/, int r/*n*/, int rt) {
    if(L == l && R == r) {
        add[rt] += c;
        return ;
    }
    push_down(rt);
    int m = (l + r) >> 1;
    if(R <= m) {
        update(L, R, c, l, m, rt<<1);
    } else if(L > m) {
        update(L, R, c, m + 1, r, rt<<1|1);
    } else {
        update(L, m, c, l, m, rt<<1);
        update(m + 1,R, c, m + 1, r, rt<<1|1);
    }
    return ;
}

int query(int pos, int l, int r, int rt) {
    if(l == r) {
        return add[rt];
    }
    push_down(rt);
    int m = (l + r) >> 1;
    if(pos <= m) {
        return query(pos, l, m, rt<<1);
    } else {
        return query(pos, m + 1, r, rt<<1|1);
    }
}


int main() {

    int l, r;
    scanf("%d %d", &n, &q);
    for(int i = 1; i <= n; ++i) {
        scanf("%I64d", &arr[i]);
    }
    build(1, n, 1);
    for(int i = 1; i <= q; ++i) {
        scanf("%d %d", &l, &r);
        update(l, r, 1, 1, n, 1);
    }
    sort(arr + 1, arr + n + 1);
    for(int i = 1; i <= n; ++i) {
        cnt[i] = query(i, 1, n, 1);
    }
    sort(cnt + 1, cnt + n + 1);
    ll ans = 0;
    for(int i = 1; i <= n; ++i) {
        ans += 1LL * arr[i] * cnt[i];
    }
    printf("%I64d\n", ans);
    return 0;
}