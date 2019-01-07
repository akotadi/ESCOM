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
//  Class Definition File: LinearEquationParser.h
//  Author: Bill Hallahan
//  Date: November 30, 2001
//
//  Abstract:
//
//    This file contains the class definition for class
//    LinearEquationParser.
//
//**********************************************************************

#ifndef LINEAREQUATIONPARSER_H
#define LINEAREQUATIONPARSER_H

#pragma warning (disable : 4786)

#include "MatrixPackage.h"
#include "CharString.h"

//======================================================================
//  Class Definition
//======================================================================

class LinearEquationParser
{
public:

    enum Status_T
    {
        SUCCESS,
        SUCCESS_NO_EQUATION,
        ERROR_ILLEGAL_EQUATION,
        ERROR_LAST_EQUATION_NOT_TERMINATED,
        ERROR_NO_EQUAL_SIGN,
        ERROR_MULTIPLE_EQUAL_SIGNS,
        ERROR_NO_TERM_BEFORE_EQUAL_SIGN,
        ERROR_NO_TERM_AFTER_EQUAL_SIGN,
        ERROR_NO_TERM_ENCOUNTERED,
        ERROR_NO_VARIABLE_IN_EQUATION,
        ERROR_MULTIPLE_DECIMAL_POINTS,
        ERROR_TOO_MANY_DIGITS,
        ERROR_MISSING_EXPONENT,
        ERROR_ILLEGAL_EXPONENT
    };

protected:

    int m_start_position;
    int m_error_position;
    Status_T m_last_error;
    int m_equation_index;
    int m_parser_state;
    bool m_negative_operator_flag;
    bool m_equal_sign_in_equation_flag;
    bool m_at_least_one_var_in_equation_flag;
    bool m_term_before_equal_sign_exists_flag;
    bool m_term_after_equal_sign_exists_flag;

public:

    typedef std::map<CharString, int> VariableNameIndexMap;

    LinearEquationParser();

    virtual ~LinearEquationParser();

    Status_T Parse(const CharString & input_line_string,
                   MatrixPackage::SparseMatrix & a_matrix,
                   MatrixPackage::SparseVector & b_vector,
                   VariableNameIndexMap & variable_name_index_map,
                   unsigned int & number_of_equations);

    void Reset();

    Status_T GetLastStatusValue() const;

    int GetErrorPosition() const;

    const char * GetStatusString(Status_T status);

protected:

    Status_T GetEquationStatus() const;

    void ResetForNewEquation();

    bool GetTerm(const CharString & input_line_string,
                 int & position,
                 MatrixPackage::SparseMatrix & a_matrix,
                 MatrixPackage::SparseVector & b_vector,
                 VariableNameIndexMap & variable_name_index_map);

    bool GetSign(const CharString & input_line_string,
                 int & position,
                 bool & negative_flag);

    bool GetNumber(const CharString & input_line_string,
                   int & position,
                   CharString & number_string);

    bool GetVariableName(const CharString & input_line_string,
                         int & position,
                         CharString & variable_name_string);

    bool GetOperator(const CharString & input_line_string,
                     int & position);

    void SkipSpaces(const CharString & input_line_string,
                    int & position);

    void SetLastStatusValue(Status_T last_error,
                            int error_position);
};

#endif
