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
//  Class Implementation File: DoubleIndex.cpp
//  Author: Bill Hallahan
//  Date: November 28, 2001
//
//  Abstract:
//
//    This file contains the class implementation for class
//    DoubleIndex.
//
//**********************************************************************

#include "DoubleIndex.h"

//======================================================================
//  Constructor: DoubleIndex::DoubleIndex
//
//  Input:
//
//    index_0          First Index
//
//    index_1          Second Index
//
//======================================================================

DoubleIndex::DoubleIndex(int index_0, int index_1)
{
    m_combined_index = (((__int64)(index_1)) << 32) | (__int64)(index_0);
}

//======================================================================
//  Destructor: DoubleIndex::DoubleIndex
//======================================================================

DoubleIndex::~DoubleIndex()
{
}

//======================================================================
//  Member Function: DoubleIndex::GetIndex0
//
//  Abstract:
//
//    This function gets index 0.
//
//
//  Input:
//
//    None
//
//  Returns:
//
//    The value of index 0 is returned.
//
//======================================================================

int DoubleIndex::GetIndex0() const
{
    return (int)(m_combined_index & 0x0FFFFFFFF);
}

//======================================================================
//  Member Function: DoubleIndex::GetIndex1
//
//  Abstract:
//
//    This function gets index 1.
//
//
//  Input:
//
//    None
//
//  Returns:
//
//    The value of index 1 is returned.
//
//======================================================================

int DoubleIndex::GetIndex1() const
{
    return (int)(m_combined_index >> 32);
}

//======================================================================
//  Operator: DoubleIndex::operator <
//
//  Abstract:
//
//    This operator performs the less than operation.
//
//
//  Input:
//
//    right_hand_side   An instance of DoubleIndex that is
//                      compared to this instance.
//
//
//  Output:
//
//    This function returns a value of type 'bool' that is
//    the value true if and only if this object is less than
//    the right_hand_side object.
//
//======================================================================

bool DoubleIndex::operator <(const DoubleIndex right_hand_side) const
{
    return m_combined_index < right_hand_side.m_combined_index;
}
