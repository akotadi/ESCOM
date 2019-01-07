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
//  Class Definition File: DoubleIndex.h
//  Author: Bill Hallahan
//  Date: November 28, 2001
//
//  Abstract:
//
//    This file contains the class definition for class DoubleIndex.
//
//**********************************************************************

#ifndef DOUBLEINDEX_H
#define DOUBLEINDEX_H

//======================================================================
//  Class Definition
//======================================================================

class DoubleIndex
{
protected:

    __int64 m_combined_index;

public:

    DoubleIndex(int index_0 = 0, int index_1 = 0);

    ~DoubleIndex();

    int GetIndex0() const;

    int GetIndex1() const;

    bool operator <(const DoubleIndex right_hand_side) const;
};

#endif
