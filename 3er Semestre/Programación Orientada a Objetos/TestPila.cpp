int main(int argc, char const *argv[])
{
	Pila *p = creaPila(5);
	push(p,1);
	push(p,2);
	push(p,3);
	pop(p);
	pop(p);
	return 0;
}