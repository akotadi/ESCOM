class Foco
{
int potencia;
int estado;

    void apagar()
    {
        estado = 0;
    }

    void prender()
    {
        estado = 1;
    }

    int tuPotencia()
    {
        return potencia;
    }
}

