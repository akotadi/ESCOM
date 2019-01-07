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
//  File: MatrixPackage.cpp
//  Author: Bill Hallahan
//  Date: November 30, 2001
//
//  Abstract:
//
//    This file contains the functions used for matrix and vector
//    calculations.
//
//**********************************************************************

#ifdef _DEBUG
#include <iostream>
#endif

#include <map>
#include "MatrixPackage.h"

//#define DUMP_NORM_RATIO

namespace
{
    // The original implementation was on a computer where the floating
    // point mantissa was 39 bits.  For that system, the value below
    // was set to 2.92E-11, which is just slightly larger than
    // 1/(2^35), which is 2.91E-11.  For my Intel system, the mantissa
    // of a double precision floating point number is 48 bits, so
    // the value is set to slightly greater than 1/(2^44).  1/(s^44)
    // evaluates to 5.68E-14, so the value 5.69E-14 is used here.
    const double f_SMALL_FLOAT = 5.69E-14;

    const char * f_SUCCESS = "Success";
    const char * f_ILL_CONDITIONED = "The equations are ill-conditioned and cannot be solved to working accuracy.";
    const char * f_SINGULAR = "Singular system of equations";
    const char * f_FLOATING_OVERFLOW = "Floating point overflow occured. Unable to solve equations.";
    const char * f_UNKNOWN = "An unknown error has occured";
};

namespace MatrixPackage
{
    //------------------------------------------------------------------
    //  Function Prototype.
    //------------------------------------------------------------------

    Status_T UnsafeSolveLinearEquations(unsigned int number_of_equations,
                                        const SparseMatrix & a_matrix,
                                        const SparseVector & b_vector,
                                        SparseVector & x_vector);

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
    //    This function catched floating point overflow exceptions
    //    and sets the return status to the value FLOATING_OVERFLOW.
    //
    //  Returns with:
    //
    //    This function returns an enumerated value of type Status_T
    //    that is the value SINGULAR if the coefficient matrix is
    //    singular to working accuracy. A value of ILL_CONDITIONED
    //    is returned if the coefficient matrix is singular to working
    //    accuracy, i.e. the floating point arithmetic does not have
    //    enough precision to guarantee convergence to an accurate
    //    solution. If a floating point overflow occurs during the
    //    calculation of the system solution then the value
    //    FLOATING_OVERFLOW is returned. The value SUCCESS is returned
    //    if the solution vector was calculated succesfully.
    //
    //==================================================================

    Status_T SolveLinearEquations(unsigned int number_of_equations,
                                  const SparseMatrix & a_matrix,
                                  const SparseVector & b_vector,
                                  SparseVector & x_vector)
    {
        Status_T status;
        try
        {
            status = UnsafeSolveLinearEquations(number_of_equations,
                                                a_matrix,
                                                b_vector,
                                                x_vector);
        }
        catch (...)
        {
            status = FLOATING_OVERFLOW;
        }

        return status;
    }

    //==================================================================
    //  Function: MatrixPackage::UnsafeSolveLinearEquations
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
    //    vector was calculated successfully
    //
    //==================================================================

    Status_T UnsafeSolveLinearEquations(unsigned int number_of_equations,
                                        const SparseMatrix & a_matrix,
                                        const SparseVector & b_vector,
                                        SparseVector & x_vector)
    {
        //--------------------------------------------------------------
        //  Matrix a_matrix is copied into working matrix
        //  a_copy_matrix.
        //--------------------------------------------------------------

        SparseMatrix a_copy_matrix = a_matrix;

        //--------------------------------------------------------------
        //  The maximum value row_maximum_value_vector[i], i = 0 to n - 1
        //  is stored
        //--------------------------------------------------------------

        SparseVector row_maximum_value_vector;
        int i = 0;
        int j = 0;

        for (i = 0; i < (int)(number_of_equations); ++i)
        {
            double temp = 0.0;

            for (j = 0; j < (int)(number_of_equations); ++j)
            {
                double test = fabs(a_matrix[DoubleIndex(i, j)]);
                
                if (test > temp)
                {
                    temp = test;
                }
            }

            row_maximum_value_vector[i] = temp;

            //----------------------------------------------------------
            //  Test for singular matrix.
            //----------------------------------------------------------

            if (temp == 0.0)
            {
                return SINGULAR;
            }
        }

        //--------------------------------------------------------------
        //  The r'th column of "l", the r'th pivotal position r', and
        //  the r'th row of "u" are determined.
        //--------------------------------------------------------------

        SparseArray<int, int> pivot_row_array;
        int r = 0;

        for (r = 0; r < (int)(number_of_equations); r++)
        {
            double maximum_value = 0.0;
            int row_maximum_value_index = r;

            //----------------------------------------------------------
            //  The l[i][r], i = r to n - 1 are determined.
            //  l[i][r] is a lower triangular matrix. It is calculated
            //  using the variable temp and copied into matrix
            //  "a_copy_matrix". The variable "maximum_value" contains
            //  the largest fabs(l[i][r] / pivot_row_array[i]) and
            //  row_maximum_value_index stores the "i" which corresponds
            //  to the value in variable maximum_value.
            //----------------------------------------------------------

            double temp = 0.0;
            int j = 0;

            for (i = r; i < (int)(number_of_equations); ++i)
            {
                temp = a_copy_matrix[DoubleIndex(i, r)];

                for (j = 0; j < r; ++j)
                {
                    temp = temp - a_copy_matrix[DoubleIndex(i, j)]
                        * a_copy_matrix[DoubleIndex(j, r)];
                }

                a_copy_matrix[DoubleIndex(i, r)] = temp;

                double test = fabs(temp / row_maximum_value_vector[i]);

                if (test > maximum_value)
                {
                    maximum_value = test;
                    row_maximum_value_index = i;
                }
            }

            //----------------------------------------------------------
            //  Test for matrix which is singular to working precision.
            //----------------------------------------------------------

            if (maximum_value == 0.0)
            {
                return ILL_CONDITIONED;
            }

            //----------------------------------------------------------
            //  "row_maximum_value_index" = r' and "pivot_row_array[r]"
            //  is the pivotal row.
            //----------------------------------------------------------

            row_maximum_value_vector[row_maximum_value_index] =
                row_maximum_value_vector[r];
            pivot_row_array[r] = row_maximum_value_index;

            //----------------------------------------------------------
            //  Rows "r" and "pivot_row_array[r]" are exchanged.
            //----------------------------------------------------------

            for (i = 0; i < (int)(number_of_equations); ++i)
            {
                temp = a_copy_matrix[DoubleIndex(r, i)];
                a_copy_matrix[DoubleIndex(r, i)] =
                    a_copy_matrix[DoubleIndex(row_maximum_value_index, i)];
                a_copy_matrix[DoubleIndex(row_maximum_value_index, i)] = temp;
            }

            //----------------------------------------------------------
            //  The u[r][i], i = r + 1 to n - 1 are determined.
            //  "u[r][i]" is an upper triangular matrix. It is copied
            //  into a_copy_matrix.
            //----------------------------------------------------------

            for (i = r + 1; i < (int)(number_of_equations); ++i)
            {
                temp = a_copy_matrix[DoubleIndex(r, i)];

                for (j = 0; j < r; ++j)
                {
                    temp = temp - a_copy_matrix[DoubleIndex(r, j)]
                        * a_copy_matrix[DoubleIndex(j, i)];
                }

                a_copy_matrix[DoubleIndex(r, i)] =
                    temp / a_copy_matrix[DoubleIndex(r, r)];
            }
        }

        //--------------------------------------------------------------
        //  The first solution vector is set equal to the null vector
        //  and the first residuals are set equal to the equation
        //  constant vector.
        //--------------------------------------------------------------

        SparseVector residual_vector;

        for (i  = 0; i < (int)(number_of_equations); ++i)
        {
            x_vector[i] = 0.0;
            residual_vector[i] = b_vector[i];
        }

        //--------------------------------------------------------------
        //  The iteration counter is initialized.
        //--------------------------------------------------------------

        int iteration = 0;
        bool not_converged_flag = true;

        do
        {
            //----------------------------------------------------------
            //  The forward substitution is performed and the solution
            //  of l y = p r is calculated where p r is the current
            //  residual after interchanges.
            //----------------------------------------------------------

            for (i = 0; i < (int)(number_of_equations); ++i)
            {
                int pivot_row = pivot_row_array[i];
                double temp = residual_vector[pivot_row];
                residual_vector[pivot_row] = residual_vector[i];

                for (j = 0; j < i; ++j)
                {
                    temp = temp - a_copy_matrix[DoubleIndex(i, j)] * residual_vector[j];
                }

                residual_vector[i] = temp / a_copy_matrix[DoubleIndex(i, i)];
            }

            //----------------------------------------------------------
            //  The back substitution is performed and the solution of
            //  u e = y is calculated. The current correction is stored
            //  in variable residual_vector.
            //----------------------------------------------------------

            for (i = number_of_equations - 1; i >= 0; i--)
            {
                double temp = residual_vector[i];

                for (j = i + 1; j < (int)(number_of_equations); ++j)
                {
                    temp = temp - a_copy_matrix[DoubleIndex(i, j)] * residual_vector[j];
                }

                residual_vector[i] = temp;
            }

            //----------------------------------------------------------
            //  The norm of the error in the residuals and the norm of
            //  the present solution vector are calculated.
            //----------------------------------------------------------

            double norm_of_x = 0.0;
            double norm_of_error = 0.0;

            for (i = 0; i < (int)(number_of_equations); ++i)
            {
                double test = fabs(x_vector[i]);

                if (test > norm_of_x)
                {
                    norm_of_x = test;
                }

                test = fabs(residual_vector[i]);

                if (test > norm_of_error)
                {
                    norm_of_error = test;
                }
            }

            //----------------------------------------------------------
            //  If iteration is zero then skip this section because
            //  no correction exists on the first iteration.
            //----------------------------------------------------------

            if (iteration != 0)
            {
                //------------------------------------------------------
                //  Test for matrix which is singular to working
                //  precision.
                //------------------------------------------------------

                if ((iteration == 1) && (norm_of_error/norm_of_x > 0.5))
                {
                    return ILL_CONDITIONED;
                }

                //------------------------------------------------------
                //  Check to see if "norm_of_error" / "norm_of_x" is greater
                //  than 2 ^ (1 - t), where "t" is the number of bits
                //  in the mantissa of a double precision number. If
                //  this is not true then the last correction is almost
                //  negligible and "not_converged_flag" is set to false.
                //------------------------------------------------------

                not_converged_flag = norm_of_error/norm_of_x >= f_SMALL_FLOAT;
#ifdef DUMP_NORM_RATIO
                double norm_ratio_for_debug = norm_of_error/norm_of_x;
                std::cout << norm_ratio_for_debug << std::endl;
#endif
            }

            //----------------------------------------------------------
            //  The corrections (residuals) are added to the
            //  solution vector.
            //----------------------------------------------------------

            for (i = 0; i < (int)(number_of_equations); ++i)
            {
                x_vector[i] = x_vector[i] + residual_vector[i];
            }

            //----------------------------------------------------------
            //  The new residuals corresponding to the solution vector
            //  are calculated.
            //----------------------------------------------------------

            for (i = 0; i < (int)(number_of_equations); ++i)
            {
                double temp = b_vector[i];

                for (j = 0; j < (int)(number_of_equations); ++j)
                {
                    temp = temp - a_matrix[DoubleIndex(i, j)] * x_vector[j];
                }

                residual_vector[i] = temp;
            }

            //----------------------------------------------------------
            //  The iteration counter is incremented and the flag
            //  "not_converged_flag" is tested. If not_converged_flag is false
            //  then the solution vector has converged to sufficient
            //  accuracy.
            //----------------------------------------------------------

            ++iteration;
        }
        while (not_converged_flag);

        return SUCCESS;
    }

    //==================================================================
    //  Member Function: MathPackage::GetStatusString
    //
    //  Abstract:
    //
    //    This function returns a status string corresponding to the
    //    passed status code.
    //
    //
    //  Input:
    //
    //    status   A status value of type Status_T.
    //
    //
    //  Output:
    //
    //    This function returns a value of type 'const char *' that
    //    is a string corresponding to the passed status code.
    //
    //==================================================================

    const char * GetStatusString(Status_T status)
    {
        const char * c_status_ptr;

        switch (status)
        {
        case SUCCESS:
            c_status_ptr = f_SUCCESS;
            break;
        case ILL_CONDITIONED:
            c_status_ptr = f_ILL_CONDITIONED;
            break;
        case SINGULAR:
            c_status_ptr = f_SINGULAR;
            break;
        case FLOATING_OVERFLOW:
            c_status_ptr = f_FLOATING_OVERFLOW;
            break;
        default:
            c_status_ptr = f_UNKNOWN;
            break;
        }

        return c_status_ptr;
    }
};
