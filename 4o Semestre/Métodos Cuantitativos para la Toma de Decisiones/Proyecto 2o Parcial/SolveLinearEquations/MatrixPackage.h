//=======================================================================
// Copyright (C) 2001-2013 William Hallahan
//
// Permission is hereby granted, free of charge, to any person
// obtaining a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify, merge,
// publish, distribute, sublicense, and/or sell copies of the Software,
// and to permit persons to whom the Software is furnished to do so,
// subject to the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
// OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
// HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
// WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
// FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
// OTHER DEALINGS IN THE SOFTWARE.
//=======================================================================

//**********************************************************************
//  File: MatrixPackage.h
//  Author: Bill Hallahan
//  Date: November 30, 2001
//
//  Abstract:
//
//    This file contains the functions used for matrix and vector
//    calculations.
//
//**********************************************************************

#ifndef MATRIXPACKAGE_H
#define MATRIXPACKAGE_H

#pragma warning (disable : 4786)

#include <math.h>
#include "SparseArray.h"
#include "DoubleIndex.h"

namespace MatrixPackage
{
    typedef SparseArray<DoubleIndex, double> SparseMatrix;
    typedef SparseArray<int, double> SparseVector;

    enum Status_T
    {
        SUCCESS,
        ILL_CONDITIONED,
        SINGULAR,
        FLOATING_OVERFLOW
    };

    //==================================================================
    //  Function: MatrixPackage::SolveLinearEquations
    //
    //  Abstract:
    //
    //    This function solves simultaneous equations in matrix form:
    //
    //        a_matrix x_vector = b_vector.
    //
    //  Call with:
    //
    //    number_of_equations  Equals the number of equations.
    //
    //    a_matrix             A square matrix
    //
    //    b_vector             A vector that contains the right hand
    //                         side of the matrix equations shown below.
    //
    //    x_vector             A vector which is the solution of
    //                         the matrix equations show below.
    //
    //        a_matrix x_vector = b_vector
    //
    //  Returns with:
    //
    //    This function returns an enumerated value of type Status_T
    //    that is the value SINGULAR if the coefficient matrix is
    //    singular to working accuracy. A value of ILL_CONDITIONED
    //    is returned if the coefficient matrix is singular to working
    //    accuracy, i.e. the floating point arithmetic does not have
    //    enough precision to guarantee convergence to an accurate
    //    solution. The value SUCCESS is returned if the solution
    //    vector was calculated succesfully.
    //
    //==================================================================

    Status_T SolveLinearEquations(unsigned int number_of_equations,
                                   const SparseMatrix & a_matrix,
                                   const SparseVector & b_vector,
                                   SparseVector & x_vector);

    const char * GetStatusString(Status_T status);
};

#endif
