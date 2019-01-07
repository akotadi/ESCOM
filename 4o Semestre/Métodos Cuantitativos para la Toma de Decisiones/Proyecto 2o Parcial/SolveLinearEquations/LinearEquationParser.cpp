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
//  Class Implementation File: LinearEquationParser.cpp
//  Author: Bill Hallahan
//  Date: November 30, 2001
//
//  Abstract:
//
//    This file contains the class implementation for class
//    LinearEquationParser.
//
//**********************************************************************

#pragma warning (disable : 4786)

#include <map>
#include "LinearEquationParser.h"

namespace
{
    enum ParserState_T
    {
        PARSE_TERM,
        PARSE_OPERATOR
    };

    unsigned int f_MAX_NUMBER_LENGTH = 20;

    const char * f_SUCCESS = "The equation was parsed successfully.";
    const char * f_ERROR_ILLEGAL_EQUATION = "The equation syntax is illegal.";
    const char * f_ERROR_LAST_EQUATION_NOT_TERMINATED = "The equation syntax is illegal. Make sure the previous equation is properly terminated.";
    const char * f_ERROR_NO_EQUAL_SIGN = "There is no equal sign in the equation.";
    const char * f_ERROR_MULTIPLE_EQUAL_SIGNS = "There are multiple equal signs in the equation.";
    const char * f_ERROR_NO_TERM_BEFORE_EQUAL_SIGN = "There is no term before the equal sign in the equation.";
    const char * f_ERROR_NO_TERM_AFTER_EQUAL_SIGN = "There is no term after the equal sign in the equation.";
    const char * f_ERROR_NO_TERM_ENCOUNTERED = "A number or a variable was expected.";
    const char * f_ERROR_NO_VARIABLE_IN_EQUATION = "There is no variable in the equation.";
    const char * f_ERROR_MULTIPLE_DECIMAL_POINTS = "A number contains more than one decimal point.";
    const char * f_ERROR_TOO_MANY_DIGITS = "A number contains more than 15 digits.";
    const char * f_ERROR_MISSING_EXPONENT = "A number contains the '^' character and is missing an exponent.";
    const char * f_ERROR_ILLEGAL_EXPONENT = "A number contains an illegal exponent.";
};

//======================================================================
//  Constructor: LinearEquationParser::LinearEquationParser
//======================================================================

LinearEquationParser::LinearEquationParser()
{
    Reset();
}

//======================================================================
//  Destructor: LinearEquationParser::~LinearEquationParser
//======================================================================

LinearEquationParser::~LinearEquationParser()
{
}

//======================================================================
//  Member Function: LinearEquationParser::Parse
//
//  Abstract:
//
//    This function parses line that contains all or part of a simple
//    linear equation. The equation contains terms separated by
//    operators. The term can be a number, a variable, or a number and
//    a variable. A term cannot be split between lines input to the
//    parser method. The operators are either the plus character '+',
//    the minus character '-', or the equal sign character '='.
//
//
//  Input:
//
//    input_line_string          The input line to be parsed
//
//    a_matrix                 The A matrix for the simultaneous
//                             equations This is updated as each line
//                             of input is parsed. See file
//                             MatricPackage.h for more information.
//
//    b_vector                 The B vector for the simultaneous
//                             equations. This is updated as each
//                             line of input is parsed. See file
//                             MatricPackage.h for more information.
//
//    variable_name_index_map  A map that stores the integer index
//                             for a variable using the variable name
//                             string as a key.
//
//    number_of_equations      A reference to an unsigned integer that
//                             is used to return the current number
//                             of equations.
//
//
//  Output:
//
//    This function returns a value of type 'Status_T' that is
//    an enum value.
//
//======================================================================

LinearEquationParser::Status_T LinearEquationParser::Parse(
                                   const CharString & input_line_string,
                                   MatrixPackage::SparseMatrix & a_matrix,
                                   MatrixPackage::SparseVector & b_vector,
                                   VariableNameIndexMap & variable_name_index_map,
                                   unsigned int & number_of_equations)
{
    //------------------------------------------------------------------
    //  Assume success status.
    //------------------------------------------------------------------

    SetLastStatusValue(SUCCESS, 0);

    //------------------------------------------------------------------
    //  Skip whitespace characters
    //------------------------------------------------------------------

    int position = 0;

    SkipSpaces(input_line_string, position);

    //------------------------------------------------------------------
    //  Save the position of the first non-whitespace character. If
    //  the first term is not found at this position then set the
    //  error status to report that it is likely that the last
    //  equation was not properly terminated.
    //------------------------------------------------------------------

    m_start_position = position;

    //------------------------------------------------------------------
    //  If the input line is empty then discard the line.
    //  If the current equation is valid then start a new equation.
    //------------------------------------------------------------------

    if (position >= (int)(input_line_string.Length()))
    {
        Status_T status = GetEquationStatus();

        if (status == SUCCESS)
        {
            ResetForNewEquation();
        }
        else if (status = SUCCESS_NO_EQUATION)
        {
            status = SUCCESS;
        }

        return status;
    }

    //------------------------------------------------------------------
    //  If the next character is a semicolon then start a new equation.
    //------------------------------------------------------------------

    if (input_line_string[position] == ';')
    {
        ++position;

        Status_T status = GetEquationStatus();

        if (status == SUCCESS)
        {
            ResetForNewEquation();
        }

        return status;
    }

    //------------------------------------------------------------------
    //  Separate the input string into tokens.
    //
    //  Variables contains the letters A through Z and the underscore
    //  '_' character.
    //
    //  Operators include plus '+', minus '-', and times '*'.
    //
    //  Delimiters include the equals sign '='.
    //
    //  Numbers include the digits 0 through 9, the decimal point
    //  (period character) '.', and the exponent character which
    //  is the letter '^E'.
    //
    //------------------------------------------------------------------

    Status_T status = SUCCESS;

    while (position < (int)(input_line_string.Length()))
    {
        //--------------------------------------------------------------
        //  Check for sequences of terms and operators.
        //
        //  TERM
        //    <SPACE> <SIGN> NUMBER <SPACE>
        //    <SPACE> <SIGN> VARIABLE <SPACE>
        //    <SPACE> <SIGN> NUMBER VARIABLE <SPACE>
        //
        //  OPERATOR
        //    <SPACE> PLUS <SPACE>
        //    <SPACE> MINUS <SPACE>
        //    <SPACE> EQUALS <SPACE>
        //
        //--------------------------------------------------------------

        while (position < (int)(input_line_string.Length()))
        {
            if (m_parser_state == PARSE_TERM)
            {
                //------------------------------------------------------
                //  Skip whitespace characters
                //------------------------------------------------------

                SkipSpaces(input_line_string, position);

                if (position < (int)(input_line_string.Length()))
                {
                    if (GetTerm(input_line_string,
                                  position,
                                  a_matrix,
                                  b_vector,
                                  variable_name_index_map))
                    {
                        m_parser_state = PARSE_OPERATOR;
                    }
                    else
                    {
                        if (GetLastStatusValue() == SUCCESS)
                        {
                            if (position == m_start_position)
                            {
                                SetLastStatusValue(ERROR_LAST_EQUATION_NOT_TERMINATED,
                                                    position);
                            }
                            else
                            {
                                SetLastStatusValue(ERROR_ILLEGAL_EQUATION,
                                                    position);
                            }
                        }

                        return GetLastStatusValue();
                    }
                }
                else
                {
                    return SUCCESS;
                }
            }
            else if (m_parser_state == PARSE_OPERATOR)
            {
                //------------------------------------------------------
                //  Check for plus sign, minus sign, equal sign, or
                //  a semicolon.
                //------------------------------------------------------

                if (! GetOperator(input_line_string, position))
                {
                    if (GetLastStatusValue() == SUCCESS)
                    {
                        if (position == m_start_position)
                        {
                            SetLastStatusValue(ERROR_LAST_EQUATION_NOT_TERMINATED,
                                                position);
                        }
                        else
                        {
                            SetLastStatusValue(ERROR_ILLEGAL_EQUATION,
                                                position);
                        }
                    }

                    return GetLastStatusValue();
                }

                m_parser_state = PARSE_TERM;
            }
        }
    }

    number_of_equations = m_equation_index;

    return status;
}

//======================================================================
//  Member Function: LinearEquationParser::Reset
//
//  Abstract:
//
//    This function resets the parser to its initial state for
//    parsing a new set of simultaneous linear equations.
//
//
//  Input:
//
//    None.
//
//  Output:
//
//    This function has no return value.
//
//======================================================================

void LinearEquationParser::Reset()
{
    m_start_position = 0;
    m_error_position = 0;
    m_last_error = SUCCESS;
    m_negative_operator_flag = false;
    m_equal_sign_in_equation_flag = false;
    m_at_least_one_var_in_equation_flag = false;
    m_term_before_equal_sign_exists_flag = false;
    m_term_after_equal_sign_exists_flag = false;
    m_parser_state = PARSE_TERM;
    m_equation_index = 0;
    
    return;
}

//======================================================================
//  Member Function: LinearEquationParser::GetLastStatusValue
//
//  Abstract:
//
//    This function returns the last status code of type Status_T.
//
//
//  Input:
//
//    None.
//
//  Output:
//
//    This function returns a value of type 'Status_T' that
//    is the last status value set by the parser.
//
//======================================================================

LinearEquationParser::Status_T LinearEquationParser::GetLastStatusValue() const
{
    return m_last_error;
}

//======================================================================
//  Member Function: LinearEquationParser::GetErrorPosition
//
//  Abstract:
//
//    This function gets the position in the input line where
//    the last error occurred.
//
//
//  Input:
//
//    None.
//
//
//  Output:
//
//    This function returns a value of type 'int' that is the position
//    in the input line where the last error occurred. This method
//    should only be called if the Parser() method returns an error
//    status value.
//
//======================================================================

int LinearEquationParser::GetErrorPosition() const
{
    return m_error_position;
}

//======================================================================
//  Member Function: LinearEquationParser::GetStatusString
//
//  Abstract:
//
//    This function returns a status string corresponding to the
//    passed status code.
//
//
//  Input:
//
//    status    A status value of type Status_T.
//
//
//  Output:
//
//    This function returns a value of type 'const char *' that is
//    an string corresponding to the passed status code.
//
//======================================================================

const char * LinearEquationParser::GetStatusString(Status_T status)
{
    const char * status_ptr = _TXT("");

    switch (status)
    {
    case SUCCESS:
    case SUCCESS_NO_EQUATION:
        status_ptr = f_SUCCESS;
        break;
    case ERROR_ILLEGAL_EQUATION:
        status_ptr = f_ERROR_ILLEGAL_EQUATION;
        break;
    case ERROR_LAST_EQUATION_NOT_TERMINATED:
        status_ptr = f_ERROR_LAST_EQUATION_NOT_TERMINATED;
        break;
    case ERROR_NO_EQUAL_SIGN:
        status_ptr = f_ERROR_NO_EQUAL_SIGN;
        break;
    case ERROR_MULTIPLE_EQUAL_SIGNS:
        status_ptr = f_ERROR_MULTIPLE_EQUAL_SIGNS;
        break;
    case ERROR_NO_TERM_BEFORE_EQUAL_SIGN:
        status_ptr = f_ERROR_NO_TERM_BEFORE_EQUAL_SIGN;
        break;
    case ERROR_NO_TERM_AFTER_EQUAL_SIGN:
        status_ptr = f_ERROR_NO_TERM_AFTER_EQUAL_SIGN;
        break;
    case ERROR_NO_TERM_ENCOUNTERED:
        status_ptr = f_ERROR_NO_TERM_ENCOUNTERED;
        break;
    case ERROR_NO_VARIABLE_IN_EQUATION:
        status_ptr = f_ERROR_NO_VARIABLE_IN_EQUATION;
        break;
    case ERROR_MULTIPLE_DECIMAL_POINTS:
        status_ptr = f_ERROR_MULTIPLE_DECIMAL_POINTS;
        break;
    case ERROR_TOO_MANY_DIGITS:
        status_ptr = f_ERROR_TOO_MANY_DIGITS;
        break;
    case ERROR_MISSING_EXPONENT:
        status_ptr = f_ERROR_MISSING_EXPONENT;
        break;
    case ERROR_ILLEGAL_EXPONENT:
        status_ptr = f_ERROR_ILLEGAL_EXPONENT;
        break;
    default:
        status_ptr = f_ERROR_ILLEGAL_EQUATION;
        break;
    }

    return status_ptr;
}

//======================================================================
//  Member Function: LinearEquationParser::GetEquationStatus
//
//  Abstract:
//
//    This function calculate the error status value for an incomplete
//    equation. This should be called if the IsCompleteEquation()
//    method returns false.
//
//
//  Input:
//
//    None.
//
//
//  Output:
//
//    This function returns a value of type 'Status_T' that is
//    the status for an incomplete equations. If the equation is
//    a complete equation then the value SUCCESS will be returned.
//
//======================================================================

LinearEquationParser::Status_T LinearEquationParser::GetEquationStatus() const
{
    Status_T status;

    if ((! m_equal_sign_in_equation_flag)
        && (!m_term_before_equal_sign_exists_flag)
        && (!m_term_after_equal_sign_exists_flag)
        && (!m_at_least_one_var_in_equation_flag))
    {
        status = SUCCESS_NO_EQUATION;
    }
    else if (!m_equal_sign_in_equation_flag)
    {
        status = ERROR_NO_EQUAL_SIGN;
    }
    else if (!m_term_before_equal_sign_exists_flag)
    {
        status = ERROR_NO_TERM_BEFORE_EQUAL_SIGN;
    }
    else if (!m_term_after_equal_sign_exists_flag)
    {
        status = ERROR_NO_TERM_AFTER_EQUAL_SIGN;
    }
    else if (!m_at_least_one_var_in_equation_flag)
    {
        status = ERROR_NO_VARIABLE_IN_EQUATION;
    }
    else
    {
        status = SUCCESS;
    }

    return status;
}

//======================================================================
//  Member Function: LinearEquationParser::ResetForNewEquation
//
//  Abstract:
//
//    This function resets the parser to process a new equation.
//
//  Input:
//
//    None.
//
//
//  Output:
//
//    This function has no return value.
//
//======================================================================

void LinearEquationParser::ResetForNewEquation()
{
    m_start_position = 0;
    m_negative_operator_flag = false;
    m_equal_sign_in_equation_flag = false;
    m_at_least_one_var_in_equation_flag = false;
    m_term_before_equal_sign_exists_flag = false;
    m_term_after_equal_sign_exists_flag = false;
    m_parser_state = PARSE_TERM;
    m_equation_index++;
    return;
}

//======================================================================
//  Member Function: LinearEquationParser::GetTerm
//
//  Abstract:
//
//    This function gets a term in the simultaneous equation. The term
//    can be a number, a variable, or a number and a variable. A term
//    cannot be split between lines input to this method.
//
//
//  Input:
//
//    input_line_string          The input line to be parsed.
//
//    position                 The current parse position in the input
//                             string.
//
//    a_matrix                 The A matrix for the simultaneous
//                             of input is equations This is updated as
//                             each line parsed. See file
//                             MatricPackage.h for more information.
//
//    b_vector                 The B vector for the simultaneous
//                             equations. This is updated as each line
//                             of input is parsed. See file
//                             MatricPackage.h for more information.
//
//    variable_name_index_map  A map that stores the integer index
//                             for a variable using the variable name
//                             string as a key.
//
//  Output:
//
//    This function returns a value of type 'bool' that is the value
//    true if and only if a term is found.
//
//======================================================================

bool LinearEquationParser::GetTerm(const CharString & input_line_string,
                                   int & position,
                                   MatrixPackage::SparseMatrix & a_matrix,
                                   MatrixPackage::SparseVector & b_vector,
                                   VariableNameIndexMap & variable_name_index_map)
{
    //------------------------------------------------------------------
    //  A term is one of the following three patterns.
    //
    //  <SPACE> <SIGN> NUMBER <SPACE>
    //  <SPACE> <SIGN> VARIABLE <SPACE>
    //  <SPACE> <SIGN> NUMBER VARIABLE <SPACE>
    //
    //  Check for a plus or a minus sign at the start of a number.
    //------------------------------------------------------------------

    bool number_is_negative_flag;

    GetSign(input_line_string,
             position,
             number_is_negative_flag);

    //------------------------------------------------------------------
    //  Skip whitespace characters
    //------------------------------------------------------------------

    SkipSpaces(input_line_string, position);

    //------------------------------------------------------------------
    //  Check to see if this is a number or a variable.
    //------------------------------------------------------------------

    CharString number_string;

    bool have_number_flag = GetNumber(input_line_string,
                                      position,
                                      number_string);

    //------------------------------------------------------------------
    //  If an error occurred then abort.
    //------------------------------------------------------------------

    if (GetLastStatusValue() != SUCCESS)
    {
        return false;
    }

    //------------------------------------------------------------------
    //  If there was a number encountered then test to see if the
    //  number has an exponent.
    //------------------------------------------------------------------

    if (have_number_flag)
    {
        if (position < (int)(input_line_string.Length()))
        {
            //----------------------------------------------------------
            //  Does the number have an exponent?
            //----------------------------------------------------------

            if (input_line_string[position] == '^')
            {
                ++position;

                //------------------------------------------------------
                //  Does the exponent have a sign.
                //------------------------------------------------------

                bool negative_exponent_flag;

                GetSign(input_line_string,
                        position,
                        negative_exponent_flag);

                //------------------------------------------------------
                //  Get the exponent digits.
                //------------------------------------------------------

                CharString exponent_string;

                if (GetNumber(input_line_string,
                              position,
                              exponent_string))
                {
                    //--------------------------------------------------
                    //  Is the exponent a valid exponent.
                    //--------------------------------------------------

                    int exponent_length = exponent_string.Length();

                    if (exponent_length <= 2)
                    {
                        bool exponent_error_flag = false;

                        for (int i = 0; i < exponent_length; ++i)
                        {
                            if (! isdigit(exponent_string[i]))
                            {
                                exponent_error_flag = true;
                            }
                        }

                        if (! exponent_error_flag)
                        {
                            number_string += 'E';

                            if (negative_exponent_flag)
                            {
                                number_string += '-';
                            }

                            number_string += exponent_string;
                        }
                        else
                        {
                            SetLastStatusValue(ERROR_ILLEGAL_EXPONENT,
                                                position);
                            return false;
                        }
                    }
                    else
                    {
                        SetLastStatusValue(ERROR_ILLEGAL_EXPONENT,
                                            position);
                        return false;
                    }
                }
                else
                {
                    SetLastStatusValue(ERROR_MISSING_EXPONENT,
                                        position);
                    return false;
                }
            }
        }
    }

    //------------------------------------------------------------------
    //  Skip whitespace characters
    //------------------------------------------------------------------

    SkipSpaces(input_line_string, position);

    CharString variable_name_string;

    bool have_variable_name_flag =
        GetVariableName(input_line_string,
                        position,
                        variable_name_string);

    //------------------------------------------------------------------
    //  Calculate the sign of the value. The sign is negated
    //  if the equals sign has been encountered.
    //------------------------------------------------------------------

    bool negative_flag =
        m_equal_sign_in_equation_flag ^ m_negative_operator_flag ^ number_is_negative_flag;

    double value;

    if (have_number_flag)
    {
        value = atof(number_string.CString());

        if (negative_flag)
        {
            value = - value;
        }
    }
    else
    {
        value = 1.0;

        if (negative_flag)
        {
            value = - value;
        }
    }

    //------------------------------------------------------------------
    //  If a variable was encountered then add to the a_matrix.
    //------------------------------------------------------------------

    bool have_term_flag;

    if (have_variable_name_flag)
    {
        //--------------------------------------------------------------
        //  If either a number or a variable is found then a term was
        //  found.
        //--------------------------------------------------------------

        have_term_flag = true;

        //--------------------------------------------------------------
        //  Each equation must have at least one variable.
        //  Record that a variable was encountered in this equation.
        //--------------------------------------------------------------

        m_at_least_one_var_in_equation_flag = true;

        //--------------------------------------------------------------
        //  If this variable has not been encountered before then add
        //  the variable to the variable_name_index_map.
        //--------------------------------------------------------------

        int variable_index = 0;

        VariableNameIndexMap::iterator it =
            variable_name_index_map.find(variable_name_string);

        if (it != variable_name_index_map.end())
        {
            //----------------------------------------------------------
            //  The variable name has already been encountered. Get
            //  the variable index for the a_matrix.
            //----------------------------------------------------------

            variable_index = (*it).second;
        }
        else
        {
            //----------------------------------------------------------
            //  This is a new variable.  Add the variable to the
            //  variable name index map.
            //----------------------------------------------------------

            variable_index = variable_name_index_map.size();
            variable_name_index_map[variable_name_string] = variable_index;
        }

        a_matrix[DoubleIndex(m_equation_index, variable_index)] =
            a_matrix[DoubleIndex(m_equation_index, variable_index)] + value;
    }
    else if (have_number_flag)
    {
        //--------------------------------------------------------------
        //  If either a number or a variable is found then a term was
        //  found.
        //--------------------------------------------------------------
        
        have_term_flag = true;

        //--------------------------------------------------------------
        //  Put the value in the B vector.
        //--------------------------------------------------------------

        b_vector[m_equation_index] = b_vector[m_equation_index] - value;
    }
    else
    {
        have_term_flag = false;
        SetLastStatusValue(ERROR_NO_TERM_ENCOUNTERED, position);
        return false;
    }

    //------------------------------------------------------------------
    //  There must be at least one term on each side of the equal sign.
    //------------------------------------------------------------------

    if (have_term_flag)
    {
        if (m_equal_sign_in_equation_flag)
        {
            m_term_after_equal_sign_exists_flag = true;
        }
        else
        {
            m_term_before_equal_sign_exists_flag = true;
        }
    }

    //------------------------------------------------------------------
    //  Skip whitespace characters
    //------------------------------------------------------------------

    SkipSpaces(input_line_string, position);

    return have_term_flag;
}

//======================================================================
//  Member Function: LinearEquationParser::GetSign
//
//  Abstract:
//
//    This function parses a plus sign character or a minus sign
//    character.
//
//
//  Input:
//
//    input_line_string  The input line to be parsed.
//
//    position           The current parse position in the input
//                       string.
//
//    negative_flag      A reference to a variable of type 'bool'
//                       that is set to the value 'true' if and only
//                       if this method encouters a minus sign.
//                       Otherwise this is set to the value 'false'.
//
//
//  Output:
//
//    This function returns a value of type 'bool' that is the value
//    'true' if and only if either a plus sign character or a minus
//    sign character is encountered.
//
//======================================================================

bool LinearEquationParser::GetSign(const CharString & input_line_string,
                                   int & position,
                                   bool & negative_flag)
{
    //------------------------------------------------------------------
    //  Check for a plus or a minus sign.
    //------------------------------------------------------------------

    bool have_sign_flag = false;
    negative_flag = false;

    if (position < (int)(input_line_string.Length()))
    {
        char c = input_line_string[position];

        if (c == '+')
        {
            have_sign_flag = true;
            ++position;
        }
        else if (c == '-')
        {
            have_sign_flag = true;
            negative_flag = true;
            ++position;
        }
    }

    return have_sign_flag;
}

//======================================================================
//  Member Function: LinearEquationParser::GetNumber
//
//  Abstract:
//
//    This function parses a number string.
//
//  Input:
//
//    input_line_string  The input line to be parsed.
//
//    position           The current parse position in the input
//                       string.
//
//    number_string      A reference to a string object that will
//                       contain the number string if a number
//                       is found by this method.
//
//  Output:
//
//    This function returns a value of type 'bool' that is the
//    value true if and only if a number is found.
//
//======================================================================

bool LinearEquationParser::GetNumber(const CharString & input_line_string,
                                     int & position,
                                     CharString & number_string)
{
    unsigned int decimal_point = 0;
    unsigned int digit_length = 0;
    bool have_number_flag = false;
    bool continue_flag = position < (int)(input_line_string.Length());

    while (continue_flag)
    {
        char c = input_line_string[position];

        continue_flag = (c == _TXT('.'));

        if (isdigit(c))
        {
            if (++digit_length > f_MAX_NUMBER_LENGTH)
            {
                SetLastStatusValue(ERROR_TOO_MANY_DIGITS, position);
                return false;
            }

            have_number_flag = true;
            number_string += c;
            ++position;
            continue_flag = position < (int)(input_line_string.Length());
        }
        else
        {
            bool continue_flag = c == _TXT('.');

            if (continue_flag)
            {
                if (++decimal_point > 1)
                {
                    SetLastStatusValue(ERROR_MULTIPLE_DECIMAL_POINTS,
                                       position);
                    return false;
                }

                number_string += c;
                ++position;
                continue_flag = position < (int)(input_line_string.Length());
            }
        }
    }

    if (number_string.Length() > f_MAX_NUMBER_LENGTH)
    {
        SetLastStatusValue(ERROR_TOO_MANY_DIGITS, position);
        return false;
    }

    return have_number_flag;
}

//======================================================================
//  Member Function: LinearEquationParser::GetVariableName
//
//  Abstract:
//
//    This function parses a variable name string.
//
//  Input:
//
//    input_line_string     The input line to be parsed.
//
//    position              The current parse position in the input
//                          string.
//
//    variable_name_string  A reference to a string object that will
//                          contain the variable name string if a
//                          variable name is found by this method.
//
//  Output:
//
//    This function returns a value of type 'bool' that is the
//    value true if and only if a variable name is found.
//
//======================================================================

bool LinearEquationParser::GetVariableName(const CharString & input_line_string,
                                           int & position,
                                           CharString & variable_name_string)
{
    bool have_variable_name_flag = false;
    bool continue_flag = position < (int)(input_line_string.Length());

    while (continue_flag)
    {
        char c = input_line_string[position];
        
        continue_flag = (isalpha(c) || c == _TXT('_'));
        
        if (continue_flag)
        {
            have_variable_name_flag = true;
            variable_name_string += c;
            ++position;
            continue_flag = position < (int)(input_line_string.Length());
        }
    }
    
    return have_variable_name_flag;
}

//======================================================================
//  Member Function: LinearEquationParser::GetOperator
//
//  Abstract:
//
//    This function parses and operator string.
//
//  Input:
//
//    input_line_string  The input line to be parsed.
//
//    position           The current parse position in the input
//                       string.
//
//  Output:
//
//    This function returns a value of type 'bool' that is the value
//    true if and only if an operator symbol is parsed.
//
//======================================================================

bool LinearEquationParser::GetOperator(const CharString & input_line_string,
                                       int & position)
{
    //------------------------------------------------------------------
    //  Skip whitespace characters
    //------------------------------------------------------------------
    
    SkipSpaces(input_line_string, position);
    
    //------------------------------------------------------
    //  If the next character is a semicolon then start a
    //  new equation.
    //------------------------------------------------------
    
    if (position < (int)(input_line_string.Length()))
    {
        if (input_line_string[position] == _TXT(';'))
        {
            ++position;

            bool success_flag = GetEquationStatus() == SUCCESS;

            if (success_flag)
            {
                ResetForNewEquation();
            }

            return success_flag;
        }
    }

    //------------------------------------------------------------------
    //  Skip whitespace characters
    //------------------------------------------------------------------

    SkipSpaces(input_line_string, position);

    //------------------------------------------------------------------
    //  Check for an equals sign.
    //------------------------------------------------------------------

    m_negative_operator_flag = false;
    bool have_equal_sign_flag = false;

    if (position < (int)(input_line_string.Length()))
    {
        if (input_line_string[position] == '=')
        {
            if (! m_equal_sign_in_equation_flag)
            {
                m_equal_sign_in_equation_flag = true;
                have_equal_sign_flag = true;
                ++position;
            }
            else
            {
                SetLastStatusValue(ERROR_MULTIPLE_EQUAL_SIGNS,
                                    position);
                return false;
            }
        }
    }

    bool have_sign_flag = GetSign(input_line_string,
                                   position,
                                   m_negative_operator_flag);

    return have_sign_flag || have_equal_sign_flag;
}

//======================================================================
//  Member Function: LinearEquationParser::SkipSpaces
//
//  Abstract:
//
//    This function skips spaces in the input string.
//
//
//  Input:
//
//    input_line_string  The input line to be parsed.
//
//    position           The current parse position in the input
//                       string.
//
//  Output:
//
//    This function has no return value.
//
//======================================================================

void LinearEquationParser::SkipSpaces(const CharString & input_line_string,
                                       int & position)
{
    bool continue_flag = position < (int)(input_line_string.Length());
    
    while (continue_flag)
    {
        char c = input_line_string[position];
        
        continue_flag = iswspace((int)(c)) != 0;
        
        if (continue_flag)
        {
            ++position;
            continue_flag = position < (int)(input_line_string.Length());
        }
    }
    
    return;
}

//======================================================================
//  Member Function: LinearEquationParser::SetLastStatusValue
//
//  Abstract:
//
//    This function sets the status value and the position that an
//    error occurs at.
//
//  Input:
//
//    last_error        A status value of type Status_T.
//
//    error_position    An integer that is the position in the current
//                      input line where an error occurred.
//
//  Output:
//
//    This function has no return value.
//
//======================================================================

void LinearEquationParser::SetLastStatusValue(Status_T last_error,
                                              int error_position)
{
    m_last_error = last_error;
    m_error_position = error_position;
    return;
}
