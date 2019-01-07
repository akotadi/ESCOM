#define TRUE 1
#define FALSE 0

typedef unsigned char boolean;

typedef struct elemento
{
	int n;
	char c;
	double d;
}elemento;

typedef struct nodo
{
	struct nodo *izq, *der;
	elemento e;
}nodo;

typedef nodo* posicion;
typedef posicion arbol_bin;

void Initialize(arbol_bin *A);
/*recibe<-árbol(A); 
Initialize (A)
Efecto: Recibe un árbol binario A y lo inicializa para su trabajo normal.
*/

void Destroy(arbol_bin *A);
/*recibe<-árbol(A); 
Destroy (A)
Efecto: Recibe un árbol binario A y lo libera completamente.
*/

posicion Root(arbol_bin *A);
/*recibe<-árbol(A); retorna -> posición
Root (A)
Efecto: Recibe un árbol binario A y retorna la posición de la raíz de A, si el árbol es vacío devuelve una posición nula.
*/

posicion Parent(arbol_bin *A, posicion P);
/*recibe<-árbol(A), posición(P); retorna -> posición
Parent(A,P)
Efecto: Recibe un árbol binario  A y una posición P, devuelve la posición de padre de p. 
Requerimientos: El árbol binario A es no vacío y la posición P es una posición valida. Si P es la raíz se devuelve una posición nula.
*/

posicion RightSon(arbol_bin *A, posicion P);
/*recibe<-árbol(A), posición(P); retorna -> posición
RightSon(A,P)
Efecto: Recibe un árbol binario A y una posición P, devuelve la posición del hijo derecho de p. 
Requerimientos: El árbol binario A es no vacío y la posición P es una posición valida. Si P no tiene hijo derecho devuelve una posición nula.
*/

posicion LeftSon(arbol_bin *A, posicion P);
/*recibe<-árbol(A), posición(P); retorna -> posición
LeftSon(A,P)
Efecto: Recibe un árbol binario A y una posición P, devuelve la posición del hijo izquierdo de p. 
Requerimientos: El árbol A es no vacío y la posición P es una posición valida. Si P no tiene hijo izquierdo devuelve una posición nula.
*/

posicion Search(arbol_bin *A, elemento e);
/*recibe<-árbol(A), elemento (E); retorna -> posición
Search(A,E)
Efecto: Recibe un árbol binario A y un elemento E, devuelve la posición del elemento E en el árbol  A.
Requerimientos: El árbol binario A es no vacío y la posición P es una posición valida. Si E no es encontrado devuelve una posición nula.
*/

boolean Empty(arbol_bin *A);
/*recibe<-árbol(A); retorna -> booleano
Empty(A)
Efecto: Recibe un árbol binario A y devuelve verdadero en caso de que el árbol A este vacío, devuelve falso en caso contrario.
*/

boolean NullNode(arbol_bin *A, posicion p);
/*recibe<-árbol(A), posición (P); retorna -> booleano
NullNode(A,P)
Efecto: Recibe un árbol binario A y una posición P, devuelve verdadero si la posición P del árbol A es nula o incorrecta y devuelve falso en caso contrario.
*/

elemento ReadNode(arbol_bin *A, posicion p);
/*recibe<-árbol(A), posición (P); retorna -> elemento
ReadNode(A,P)
Efecto: Recibe un árbol binario A y una posición P, devuelve el elemento en la posición P del árbol A.
Requerimientos: El árbol A es no vacío y la posición P es una posición valida..
*/

void NewRightSon(arbol_bin *A, posicion p, elemento e);
/*recibe<-árbol(A), posición (P), elemento E; 
NewRightSon(A,P,E)
Efecto: Recibe un árbol binario A, una posición P y un elemento E, se añade un nodo que contenga E como hijo derecho del nodo con posición P.
Requerimientos: El árbol binario A es no vacío y la posición P es una posición valida. Si el árbol A es vacío se agrega a un nodo raíz con E. si P ya tiene un hijo derecho, se cancela la operación.
*/

void NewLeftSon(arbol_bin *A, posicion p, elemento e);
/*recibe<-árbol(A), posición (P), elemento E; 
NewLeftSon(A,P,E)
Efecto: Recibe un árbol binario A, una posición P y un elemento E, se añade un nodo que contenga E como hijo izquierdo del nodo con posición P.
Requerimientos: El árbol binario A es no vacío y la posición P es una posición valida. Si el árbol A es vacío se agrega a un nodo raíz con E; si P ya tiene un hijo Izquierdo, se cancela la operación.
*/

void DeleteRightSon(arbol_bin *A, posicion p);
/*recibe<-árbol(A), posición (P);
DeleteRightSon(A,P)
Efecto: Recibe un árbol binario A y una posición se elimina al hijo derecho y todos sus descendientes del nodo con posición P.
Requerimientos: El árbol A es no vacío y la posición P es una posición valida y tiene un hijo derecho.
*/

void DeleteLeftSon(arbol_bin *A, posicion p);
/*recibe<-árbol(A), posición (P);
DeleteLeftSon(A,P)
Efecto: Recibe un árbol binario A y una posición se elimina al hijo izquierdo y todos sus descendientes del nodo con posición P.
Requerimientos: El árbol A es no vacío y la posición P es una posición valida y tiene un hijo izquierdo.
*/

void DeleteNode(arbol_bin *A, posicion p);
/*recibe<-árbol(A), posición (P);
DeleteNode(A,P)
Efecto: Recibe un árbol binario A y una posición P, se elimina al nodo con posición  P  y todos sus descendientes.
Requerimientos: El árbol A es no vacío y la posición P es una posición valida. 
*/

void ReplaceNode(arbol_bin *A, posicion p, elemento e);
/*recibe<-árbol(A), posición (P), elemento (E);
ReplaceNode(A,P)
Efecto: Recibe un árbol binario A, una posición P y un elemento E, se remplaza a E del nodo con posición P en A.
Requerimientos: El árbol binario A es no vacío y la posición P es una posición valida. 
*/

