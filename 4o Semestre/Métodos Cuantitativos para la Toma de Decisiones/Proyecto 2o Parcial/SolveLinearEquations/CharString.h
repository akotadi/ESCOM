//=======================================================================
// Copyright (C) 2006-2013 William Hallahan
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
//  Class Definition File: CharString.h
//  Author: Bill Hallahan
//  Date: July 3, 2006
//
//  Abstract:
//
//    This file contains the definition for class CharString.
//
//**********************************************************************

#ifndef CHARSTRING_H
#define CHARSTRING_H

#include <string>

#ifdef UNICODE
typedef wchar_t CharType;
#define _TXT(P) L##P
#else
typedef char CharType;
#define _TXT(P) P
#endif

//======================================================================
//  Class Definition
//======================================================================

class CharString
{
protected:

    typedef std::basic_string<CharType> StringType;
        
    mutable StringType m_string;

public:

#ifndef UNICODE

    friend std::istream & operator >>(std::istream & is,
                                      CharString & char_string);

    friend std::ostream & operator <<(std::ostream & os,
                                      const CharString & char_string);
#endif

    CharString();

    CharString(CharType character);

    CharString(const CharType * character_ptr);

    CharString(const CharType * character_ptr, int length);

    CharString(const CharString & char_string);

protected:

    explicit CharString(const std::basic_string<CharType> & std_string);

public:

    virtual ~CharString();

    const CharType * CString() const;

    void Reserve(size_t count);

    size_t Capacity() const;

    void Clear();

    void Assign(CharType character);

    void Assign(const CharType * character_ptr,
                size_t length = (size_t)(-1));

    void Assign(const CharString & char_string);

    void Append(CharType character);

    void Append(const CharType * character_ptr,
                size_t length = (size_t)(-1));

    void Append(const CharString & char_string);

    void Insert(int index, CharType character);

    void Insert(int index,
                const CharType * character_ptr,
                size_t length = (size_t)(-1));

    void Insert(int index, const CharString & char_string);

    size_t Length() const;

    bool IsEmpty() const;

    void SetEmpty();

    CharType GetCharacter(unsigned int index) const;

    void SetCharacter(unsigned int index, CharType character);

    int Compare(const CharType * character_ptr)  const;

    int Compare(const CharString & char_string) const;

    int CompareIgnoreCase(const CharType * character_ptr) const;

    int CompareIgnoreCase(const CharString & char_string) const;

    CharString ExtractMiddle(unsigned int count, unsigned int length = -1) const;

    CharString ExtractLeading(unsigned int count) const;

    CharString ExtractTrailing(unsigned int count) const;

    CharString ExtractIncluding(CharType character) const;

    CharString ExtractIncluding(const CharType * character_ptr) const;

    CharString ExtractIncluding(const CharString & char_string) const;

    CharString ExtractExcluding(CharType character) const;

    CharString ExtractExcluding(const CharType * character_ptr) const;

    CharString ExtractExcluding(const CharString & char_string) const;

    void MakeUpperCase();

    void MakeLowerCase();

    void Reverse();

    void Trim(CharType character);

    void Trim(const CharType * character_ptr = _TXT(" \t"));

    void Trim(const CharString & char_string);

    void TrimLeading(CharType character);

    void TrimLeading(const CharType * character_ptr = _TXT(" \t"));

    void TrimLeading(const CharString & char_string);

    void TrimTrailing(CharType character);

    void TrimTrailing(const CharType * character_ptr = _TXT(" \t"));

    void TrimTrailing(const CharString & char_string);

    int Find(CharType character, unsigned int start = 0) const;

    int Find(const CharType * character_ptr, unsigned int start = 0) const;

    int Find(const CharString & char_string, unsigned int start = 0) const;

    int ReverseFind(CharType character, unsigned int start = -1) const;

    int ReverseFind(const CharType * character_ptr, unsigned int start = -1) const;

    int ReverseFind(const CharString & char_string, unsigned int start = -1) const;

    int FindFirstOf(CharType character, unsigned int start = 0) const;

    int FindFirstOf(const CharType * character_ptr, unsigned int start = 0) const;

    int FindFirstOf(const CharString & char_string, unsigned int start = 0) const;

    int FindLastOf(CharType character, unsigned int start = -1) const;

    int FindLastOf(const CharType * character_ptr, unsigned int start = -1) const;

    int FindLastOf(const CharString & char_string, unsigned int start = -1) const;

    CharType operator [](unsigned int index) const;

    CharString operator =(CharType character);

    CharString operator =(const CharType * character_ptr);

    CharString operator =(const CharString & char_string);

    CharString operator +=(CharType character);

    CharString operator +=(const CharType * character_ptr);

    CharString operator +=(const CharString & char_string);

    CharString & operator +=(bool value_flag);

    CharString & operator +=(short s_value);

#ifndef UNICODE
    CharString & operator +=(unsigned short us_value);
#endif

    CharString & operator +=(int i_value);

    CharString & operator +=(unsigned int ui_value);

    CharString & operator +=(long l_value);

    CharString & operator +=(unsigned long ul_value);

    CharString & operator +=(float f_value);

    CharString & operator +=(double d_value);

    CharString & operator +=(long double ld_value);

    CharString & operator +=(void * value_ptr);
};

//======================================================================
//  Global operators.
//======================================================================

CharString operator +(const CharString & left_char_string,
					  const CharString & right_char_string);

CharString operator +(const CharString & char_string,
					  const CharType * character_ptr);

CharString operator +(const CharType * character_ptr,
					  const CharString & char_string);

CharString operator +(const CharString & char_string,
					  CharType character);

CharString operator +(CharType character,
					  const CharString & char_string);

bool operator ==(const CharString & left_char_string,
                 const CharString & right_char_string);

bool operator !=(const CharString & left_char_string,
                 const CharString & right_char_string);

bool operator <(const CharString & left_char_string,
                const CharString & right_char_string);

bool operator >(const CharString & left_char_string,
                const CharString & right_char_string);

bool operator <=(const CharString & left_char_string,
                 const CharString & right_char_string);

bool operator >=(const CharString & left_char_string,
                 const CharString & right_char_string);

#endif
