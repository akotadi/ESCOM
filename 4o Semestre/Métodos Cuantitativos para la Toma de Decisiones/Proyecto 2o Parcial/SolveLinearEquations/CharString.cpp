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
//  Class Implementation File: CharString.cpp
//  Author: Bill Hallahan
//  Date: July 3, 2006
//
//  Abstract:
//
//    This file contains the implementation for class CharString.
//
//**********************************************************************

#ifdef WIN32
#include <tchar.h> // For _tcslen()
#endif

#ifdef Yu
#include <stdafx.h>
#endif

#ifdef __linux__
#include <string.h>
typedef char TCHAR;
#define _tcslen(p) strlen(p)
#endif

#include <iostream>
#include <sstream>
#include "CharString.h"

// String stream type definition
typedef std::basic_stringstream<CharType> StringStreamType;

//======================================================================
//  Local declarations
//======================================================================

namespace
{
    const CharType * f_true_ptr = _TXT("true");
    const CharType * f_false_ptr = _TXT("false");
};

//======================================================================
//  Constructor: CharString::CharString
//======================================================================

CharString::CharString()
{
}

//======================================================================
//  Constructor: CharString::CharString
//======================================================================

CharString::CharString(CharType character)
{
    Assign(character);
}

//======================================================================
//  Constructor: CharString::CharString
//======================================================================

CharString::CharString(const CharType * character_ptr)
{
    m_string = character_ptr;
}


//======================================================================
//  Constructor: CharString::CharString
//======================================================================

CharString::CharString(const CharType * character_ptr, int length)
{
    Assign(character_ptr, length);
}

//======================================================================
//  Constructor: CharString::CharString
//======================================================================

CharString::CharString(const CharString & char_string)
{
    Assign(char_string);
}

//======================================================================
//  Protected constructor: CharString::CharString
//======================================================================

CharString::CharString(const std::basic_string<CharType> & std_string)
{
    m_string.assign(std_string);
}

//======================================================================
//  Destructor: CharString::~CharString
//======================================================================

CharString::~CharString()
{
}

//======================================================================
//  Member Function: CharString::CString
//======================================================================

const CharType * CharString::CString() const
{
#ifdef UNICODE
    // Cannot use std:strings c_str method here, because
    // it's not guaranteed to work for wide characters.
    size_t length = m_string.length();

    if (length + 1 > m_string.capacity())
    {
        std::basic_string<TCHAR> temp_string = m_string;
        m_string.reserve(length + 1);
        m_string = temp_string;
    }

    m_string[length] = 0;

    return m_string.data();
#else
    return m_string.c_str();
#endif
}

//======================================================================
//  Member Function: CharString::Reserve
//======================================================================

void CharString::Reserve(size_t count)
{
    m_string.reserve(count);
}

//======================================================================
//  Member Function: CharString::Capacity
//======================================================================

size_t CharString::Capacity() const
{
    return m_string.capacity();
}

//======================================================================
//  Member Function: CharString::Clear
//======================================================================

void CharString::Clear()
{
    m_string = _TXT("");
}

//======================================================================
//  Member Function: CharString::Assign
//======================================================================

void CharString::Assign(CharType character)
{
    m_string.assign(1, character);
}

//======================================================================
//  Member Function: CharString::Assign
//======================================================================

void CharString::Assign(const CharType * character_ptr, size_t length)
{
    if ((int)(length) == -1)
    {
        length = _tcslen(character_ptr);
    }

    m_string.assign(character_ptr, length);
}

//======================================================================
//  Member Function: CharString::Assign
//======================================================================

void CharString::Assign(const CharString & char_string)
{
    m_string.assign(char_string.CString(),
                    char_string.Length());
}

//======================================================================
//  Member Function: CharString::Append
//======================================================================

void CharString::Append(CharType character)
{
	m_string.append(1, character);
}

//======================================================================
//  Member Function: CharString::Append
//======================================================================

void CharString::Append(const CharType * character_ptr, size_t length)
{
    if ((int)(length) == -1)
    {
        length = _tcslen(character_ptr);
    }

	m_string.append(character_ptr, length);
}

//======================================================================
//  Member Function: CharString::Append
//======================================================================

void CharString::Append(const CharString & char_string)
{
	m_string.append(char_string.CString(),
                    char_string.Length());
}

//======================================================================
//  Member Function: CharString::Insert
//======================================================================

void CharString::Insert(int index, CharType character)
{
    m_string.insert(index, 1, character);
}

//======================================================================
//  Member Function: CharString::Insert
//======================================================================

void CharString::Insert(int index,
                        const CharType * character_ptr,
                        size_t length)
{
    if ((int)(length) == -1)
    {
        length = _tcslen(character_ptr);
    }

    m_string.insert(index, character_ptr, length);
}

//======================================================================
//  Member Function: CharString::Insert
//======================================================================

void CharString::Insert(int index, const CharString & char_string)
{
    m_string.insert(index,
                    char_string.CString(),
                    char_string.Length());
}

//======================================================================
//  Member Function: CharString::Length
//======================================================================

size_t CharString::Length() const
{
	return m_string.length();
}

//======================================================================
//  Member Function: CharString::IsEmpty
//
//  Abstract:
//
//    This method returns the value true if and only if the
//    string is empty.
//
//
//  Input:
//
//    None.
//
//  Output:
//
//    This method returns a value of type 'bool' that is true
//    if and only if  the string is empty.
//
//======================================================================

bool CharString::IsEmpty() const
{
	bool is_empty = m_string.empty();

	if (!is_empty)
	{
		is_empty = m_string == _TXT("");
	}

	return is_empty;
}

//======================================================================
//  Member Function: CharString::SetEmpty
//
//  Abstract:
//
//    This method sets the string to the empty state.
//
//
//  Input:
//
//    None.
//
//  Output:
//
//    This method does not return a value.
//
//======================================================================

void CharString::SetEmpty()
{
    m_string.erase();
}

//======================================================================
//  Member Function: CharString::GetCharacter
//
//  Abstract:
//
//    This method returns the character at a given index.
//
//
//  Input:
//
//    index    A value of type 'int' that is a string index.
//
//
//  Output:
//
//    This method returns a value of type 'CharType' that is the
//    character at a given index or the value zero if the index
//    is out of range.
//
//======================================================================

CharType CharString::GetCharacter(unsigned int index) const
{
	CharType character = 0;

	if ((index >= 0) && (index < m_string.length()))
	{
		character = m_string[index];
	}

	return character;
}

//======================================================================
//  Member Function: CharString::SetCharacter
//
//  Abstract:
//
//    This method sets the character at a given index. The index
//    must refer to a character in the current string.
//
//
//  Input:
//
//    index             A value of type 'int' that is a character index.
//
//    character         A value of type 'CharType'
//
//
//  Output:
//
//    This method does not return a value.
//
//======================================================================

void CharString::SetCharacter(unsigned int index, CharType character)
{
    if ((index >= 0) && (index < m_string.length()))
    {
        m_string[index] = character;
    }
}

//======================================================================
//  Member Function: CharString::Compare
//
//  Abstract:
//
//    This method compares this string against a passed string.
//
//
//  Input:
//
//    character_ptr    A pointer to a value of type 'const CharType'
//
//
//  Output:
//
//    This method returns a value of type 'int' that is zero
//    if the strings are equal, positive is this string is
//    greater than the passed string and negative if the string
//    is less than the passed string.
//
//======================================================================

int CharString::Compare(const CharType * character_ptr) const
{
    return m_string.compare(character_ptr);
}

//======================================================================
//  Member Function: CharString::Compare
//
//  Abstract:
//
//    This method compares this string and the passed string
//
//
//  Input:
//
//    char_string    A value of type 'const CharString &'
//
//
//  Output:
//
//    This method returns a value of type 'int' that is zero
//    if the strings are equal, positive is this string is
//    greater than the passed string and negative if the string
//    is less than the passed string.
//
//======================================================================

int CharString::Compare(const CharString & char_string) const
{
    return m_string.compare(char_string.CString());
}

//======================================================================
//  Member Function: CharString::CompareIgnoreCase
//
//  Abstract:
//
//    This method compares this string and the passed string
//
//
//  Input:
//
//    character_ptr    A pointer to a value of type 'const CharType'
//
//
//  Output:
//
//    This method returns a value of type 'int' that is zero
//    if the strings are equal, positive is this string is
//    greater than the passed string and negative if the string
//    is less than the passed string.
//
//======================================================================

int CharString::CompareIgnoreCase(const CharType * character_ptr) const
{
    CharString test_string(character_ptr);
    test_string.MakeLowerCase();
    CharString this_string(*this);
    this_string.MakeLowerCase();
    return this_string.Compare(test_string);
}

//======================================================================
//  Member Function: CharString::CompareIgnoreCase
//
//  Abstract:
//
//    This method compares this string and the passed string
//
//
//  Input:
//
//    char_string    A value of type 'const CharString &'
//
//
//  Output:
//
//    This method returns a value of type 'int' that is zero
//    if the strings are equal, positive is this string is
//    greater than the passed string and negative if the string
//    is less than the passed string.
//
//======================================================================

int CharString::CompareIgnoreCase(const CharString & char_string) const
{
    CharString test_string(char_string);
    test_string.MakeLowerCase();
    CharString this_string(*this);
    this_string.MakeLowerCase();
    return this_string.Compare(test_string);
}

//======================================================================
//  Member Function: CharString::ExtractMiddle
//
//  Abstract:
//
//    This method returns a string containing a specified
//    number of characters starting at the specified index.
//
//
//  Input:
//
//    count     A value of type 'unsigned int' that specifies
//              where to start to extract characters.
//
//    length    A value of type 'unsigned int' that defaults
//              to the value -1, which indicates to
//              return to the end of the string.
//
//
//  Output:
//
//    This method returns a value of type 'CharString'
//
//======================================================================

CharString CharString::ExtractMiddle(unsigned int start, unsigned int count) const
{
    return CharString(m_string.substr(start, count));
}

//======================================================================
//  Member Function: CharString::ExtractLeading
//
//  Abstract:
//
//    This method returns a string that contains the specified
//    number of leading characters.
//
//
//  Input:
//
//    count    A value of type 'unsigned int'
//
//
//  Output:
//
//    This method returns a value of type 'CharString' that contains
//    the specified number of leading characters.
//
//======================================================================

CharString CharString::ExtractLeading(unsigned int count) const
{
    return CharString(m_string.substr(0, count));
}

//======================================================================
//  Member Function: CharString::ExtractTrailing
//
//  Abstract:
//
//    This method returns a string that contains the trailing
//    characters starting at the specified position.
//
//
//  Input:
//
//    position    A value of type 'int'
//
//
//  Output:
//
//    This method returns a value of type 'CharString' that contains
//    the trailing characters starting at the specified position.
//
//======================================================================

CharString CharString::ExtractTrailing(unsigned int position) const
{
    CharString char_string;

    size_t length = m_string.length();

    if (position <= length - 1)
    {
        char_string = CharString(m_string.substr(position, length - position));
    }

    return char_string;
}

//======================================================================
//  Member Function: CharString::ExtractIncluding
//
//  Abstract:
//
//    This method returns a string that contains the extracted
//    characters that match the passed character.
//
//
//  Input:
//
//    character    A value of type 'CharType'
//
//
//  Output:
//
//    This method returns a value of type 'CharString' that contains
//    the extracted characters that match the passed character.
//
//======================================================================

CharString CharString::ExtractIncluding(CharType character) const
{
    CharString extracted_char_string;

    size_t length = Length();

    for (size_t i = 0; i < length; ++i)
    {
        if (m_string[i] == character)
        {
            extracted_char_string.Append(character);
        }
    }

    return extracted_char_string;
}

//======================================================================
//  Member Function: CharString::ExtractIncluding
//
//  Abstract:
//
//    This method returns a string that contains the extracted
//    characters that match any characters in the passed character set.
//
//
//  Input:
//
//    character_ptr    A pointer to a value of type 'const CharType *'
//                     points to the list of characters to be extracted.
//
//
//  Output:
//
//    This method returns a value of type 'CharString' that contains
//    the extracted characters that match the passed character.
//
//======================================================================

CharString CharString::ExtractIncluding(const CharType * character_ptr) const
{
    CharString extracted_char_string;

    if (character_ptr != NULL)
    {
        size_t length = Length();

        size_t string_length = _tcslen(character_ptr);

        for (size_t i = 0; i < length; ++i)
        {
            bool found = false;

            CharType current_char = m_string[i];

            for (size_t i = 0; i < string_length; ++i)
            {
                if (current_char == character_ptr[i])
                {
                    found = true;
                    break;
                }
            }

            if (found)
            {
                extracted_char_string.Append(current_char);
            }
        }
    }

    return extracted_char_string;
}

//======================================================================
//  Member Function: CharString::ExtractIncluding
//
//  Abstract:
//
//    This method returns a string that contains the extracted
//    characters that match any characters in the passed character set.
//
//
//  Input:
//
//    char_string    A pointer to a value of type 'const CharString'
//
//
//  Output:
//
//    This method returns a value of type 'CharString' that contains
//    the extracted characters that match the passed character.
//
//======================================================================

CharString CharString::ExtractIncluding(const CharString & char_string) const
{
    CharString extracted_char_string;

    size_t length = Length();

    for (size_t i = 0; i < length; ++i)
    {
        bool found = false;

        CharType current_char = m_string[i];

        for (unsigned int i = 0;
            i < static_cast<unsigned int>(char_string.Length());
            ++i)
        {
            if (current_char == char_string[i])
            {
                found = true;
                break;
            }
        }

        if (found)
        {
            extracted_char_string.Append(current_char);
        }
    }

    return extracted_char_string;
}

//======================================================================
//  Member Function: CharString::ExtractExcluding
//
//  Abstract:
//
//    This method returns a string that does not contain the
//    characters that match the passed character.
//
//
//  Input:
//
//    character         A value of type 'CharType'
//
//
//  Output:
//
//    This method returns a value of type 'CharString' that does
//    not contain the characters that match the passed character.
//
//======================================================================

CharString CharString::ExtractExcluding(CharType character) const
{
    CharString extracted_char_string;

    size_t length = Length();

    for (size_t i = 0; i < length; ++i)
    {
        if (m_string[i] != character)
        {
            extracted_char_string.Append(character);
        }
    }

    return extracted_char_string;
}

//======================================================================
//  Member Function: CharString::ExtractExcluding
//
//  Abstract:
//
//    This method returns a string that does not contain the
//    characters that match any characters in the passed character set.
//
//
//  Input:
//
//    character_ptr    A pointer to a value of type 'const CharType'
//
//
//  Output:
//
//    This method returns a value of type 'CharString' that does not
//    contain the characters that match any characters in the passed
//    character set.
//
//======================================================================

CharString CharString::ExtractExcluding(const CharType * character_ptr) const
{
    CharString extracted_char_string;

    if (character_ptr != NULL)
    {
        size_t length = Length();

        size_t string_length = _tcslen(character_ptr);

        for (size_t i = 0; i < length; ++i)
        {
            bool found = false;

            CharType current_char = m_string[i];

            for (size_t i = 0; i < string_length; ++i)
            {
                if (current_char == character_ptr[i])
                {
                    found = true;
                    break;
                }
            }

            if (!found)
            {
                extracted_char_string.Append(current_char);
            }
        }
    }

    return extracted_char_string;
}

//======================================================================
//  Member Function: CharString::ExtractExcluding
//
//  Abstract:
//
//    This method returns a string that does not contain the
//    characters that match any characters in the passed character set.
//
//
//  Input:
//
//    char_string    A pointer to a value of type 'const CharString'
//
//
//  Output:
//
//    This method returns a value of type 'CharString' that does not
//    contain the characters that match any characters in the passed
//    character set.
//
//======================================================================

CharString CharString::ExtractExcluding(const CharString & char_string) const
{
    CharString extracted_char_string;

    size_t length = Length();

    for (size_t i = 0; i < length; ++i)
    {
        bool found = false;

        CharType current_char = m_string[i];

        for (unsigned int i = 0; i < char_string.Length(); ++i)
        {
            if (current_char == char_string[i])
            {
                found = true;
                break;
            }
        }

        if (!found)
        {
            extracted_char_string.Append(current_char);
        }
    }

    return extracted_char_string;
}

//======================================================================
//  Member Function: CharString::MakeUpperCase
//
//  Abstract:
//
//    This function converts this string to all upper-case
//    characters.
//
//
//  Input:
//
//    None.
//
//  Output:
//
//    This method does not return a value.
//
//======================================================================

void CharString::MakeUpperCase()
{
    CharType * c_data_ptr = const_cast<CharType *>(m_string.data());

    size_t length = m_string.length();

    for (size_t i = 0; i < length; ++i)
    {
        c_data_ptr[i] = (CharType)(toupper(c_data_ptr[i]));
    }
}

//======================================================================
//  Member Function: CharString::MakeLowerCase
//
//  Abstract:
//
//    This function converts this string to all lower-case
//    characters.
//
//
//  Input:
//
//    None.
//
//  Output:
//
//    This method does not return a value.
//
//======================================================================

void CharString::MakeLowerCase()
{
    CharType * c_data_ptr = const_cast<CharType *>(m_string.data());

    size_t length = m_string.length();

    for (size_t i = 0; i < length; ++i)
    {
        c_data_ptr[i] = (CharType)(tolower(c_data_ptr[i]));
    }
}


//======================================================================
//  Member Function: CharString::Reverse
//
//  Abstract:
//
//    This method reverses all characters in the string.
//
//
//  Input:
//
//    None.
//
//  Output:
//
//    This method does not return a value.
//
//======================================================================

void CharString::Reverse()
{
    CharType * c_data_ptr = const_cast<CharType *>(m_string.data());

    size_t length = m_string.length();

    for (size_t i = 0; i < length >> 1; ++i)
    {
        CharType temp = c_data_ptr[i];
        c_data_ptr[i] = c_data_ptr[length - i];
        c_data_ptr[length - i] = temp;
    }
}

//======================================================================
//  Member Function: CharString::Trim
//
//  Abstract:
//
//    This method removes the specified characters from
//    the start of the string.
//
//
//  Input:
//
//    character         A value of type 'CharType'
//
//
//  Output:
//
//    This method does not return a value.
//
//======================================================================

void CharString::Trim(CharType character)
{
    TrimLeading(character);
    TrimTrailing(character);
}

//======================================================================
//  Member Function: CharString::Trim
//
//  Abstract:
//
//    This method removes all of the specified characters from
//    the start of the string.
//
//
//  Input:
//
//    character_ptr    A pointer to a value of type 'const CharType'
//
//
//  Output:
//
//    This method does not return a value.
//
//======================================================================

void CharString::Trim(const CharType * character_ptr)
{
    TrimLeading(character_ptr);
    TrimTrailing(character_ptr);
}

//======================================================================
//  Member Function: CharString::Trim
//
//  Abstract:
//
//    This method removes all of the specified characters from
//    the start of the string.
//
//
//  Input:
//
//    char_string    A pointer to a value of type 'const CharString'
//
//
//  Output:
//
//    This method does not return a value.
//
//======================================================================

void CharString::Trim(const CharString & char_string)
{
    TrimLeading(char_string);
    TrimTrailing(char_string);
}

//======================================================================
//  Member Function: CharString::TrimLeading
//
//  Abstract:
//
//    This method removes the specified characters from
//    the start of the string.
//
//
//  Input:
//
//    character         A value of type 'CharType'
//
//
//  Output:
//
//    This method does not return a value.
//
//======================================================================

void CharString::TrimLeading(CharType character)
{
    size_t index = 0;

    size_t length = m_string.length();

    while ((index < length) && (m_string[index] == character))
    {
        ++index;
    }

    m_string = m_string.substr(index);
}

//======================================================================
//  Member Function: CharString::TrimLeading
//
//  Abstract:
//
//    This method removes all of the specified characters from
//    the start of the string.
//
//
//  Input:
//
//    character_ptr    A pointer to a value of type 'const CharType'
//
//
//  Output:
//
//    This method does not return a value.
//
//======================================================================

void CharString::TrimLeading(const CharType * character_ptr)
{
    if (character_ptr != NULL)
    {
        int char_string_length = _tcslen(character_ptr);

        if (char_string_length > 0)
        {
            int this_string_length = m_string.length();

            if (this_string_length > 0)
            {
                int found_count = 0;

                for (int i = 0; i < this_string_length; ++i)
                {
                    CharType current_char = m_string[i];

                    bool is_match = false;

                    for (int j = 0; j < char_string_length; ++j)
                    {
                        if (current_char == character_ptr[j])
                        {
                            ++found_count;
                            is_match = true;
                        }
                    }

                    if (!is_match)
                    {
                        break;
                    }
                }

                // If the passed string contains more than one of the same characters
                // then it's possible for found_count to be greater than the length
                // of the passed string.
                if (found_count > this_string_length)
                {
                    found_count = this_string_length;
                }

                m_string = m_string.substr(found_count);
            }
        }
    }
}

//======================================================================
//  Member Function: CharString::TrimLeading
//
//  Abstract:
//
//    This method removes all of the specified characters from
//    the start of the string.
//
//
//  Input:
//
//    char_string    A pointer to a value of type 'const CharString'
//
//
//  Output:
//
//    This method does not return a value.
//
//======================================================================

void CharString::TrimLeading(const CharString & char_string)
{
    TrimLeading(char_string.CString());
}

//======================================================================
//  Member Function: CharString::TrimTrailing
//
//  Abstract:
//
//    This method removes athe specified characters from
//    the end of the string.
//
//
//  Input:
//
//    character         A value of type 'CharType'
//
//
//  Output:
//
//    This method does not return a value.
//
//======================================================================

void CharString::TrimTrailing(CharType character)
{
    size_t length = m_string.length();

    size_t index = length - 1;

    while ((index >= 0) && (m_string[index] == character))
    {
        --index;
    }

    m_string = m_string.substr(0, index);
}

//======================================================================
//  Member Function: CharString::TrimTrailing
//
//  Abstract:
//
//    This method removes all of the specified characters from
//    the end of the string.
//
//
//  Input:
//
//    character_ptr    A pointer to a value of type 'const CharType'
//
//
//  Output:
//
//    This method does not return a value.
//
//======================================================================

void CharString::TrimTrailing(const CharType * character_ptr)
{
    if (character_ptr != NULL)
    {
        int char_string_length = _tcslen(character_ptr);

        if (char_string_length > 0)
        {
            int this_string_length = m_string.length();

            if (this_string_length > 0)
            {
                int found_count = 0;

                for (size_t i = this_string_length - 1; i >= 0 ; --i)
                {
                    CharType current_char = m_string[i];

                    bool is_match = false;

                    for (int j = 0; j < char_string_length; ++j)
                    {
                        if (current_char == character_ptr[j])
                        {
                            ++found_count;
                            is_match = true;
                        }
                    }

                    if (!is_match)
                    {
                        break;
                    }
                }

                // If the passed string contains more than one of the same characters
                // then it's possible for found_count to be greater than the length
                // of the passed string.
                if (found_count > this_string_length)
                {
                    found_count = this_string_length;
                }

                m_string = m_string.substr(0, this_string_length - found_count);
            }
        }
    }
}

//======================================================================
//  Member Function: CharString::TrimTrailing
//
//  Abstract:
//
//    This method removes all of the specified characters from
//    the end of the string.
//
//
//  Input:
//
//    char_string    A pointer to a value of type 'const CharString'
//
//
//  Output:
//
//    This method does not return a value.
//
//======================================================================

void CharString::TrimTrailing(const CharString & char_string)
{
    TrimTrailing(char_string.CString());
}

//======================================================================
//  Member Function: CharString::Find
//
//  Abstract:
//
//    This method returns the position of the specified
//    character. The value -1 is returned if the character
//    is not found at, or after, the specified start position.
//
//
//  Input:
//
//    character         A value of type 'CharType'
//
//    start             A value of type 'unsigned int' that
//                      is the  position to start the search.
//
//  Output:
//
//    This method returns a value of type 'int' that is the position
//    of the specified character and the value -1 if the character
//    is not in this at, or after, the start position.
//
//======================================================================

int CharString::Find(CharType character, unsigned int start) const
{
    return static_cast<int>(m_string.find(character, start));
}

//======================================================================
//  Member Function: CharString::Find
//
//  Abstract:
//
//    This method returns the position of the specified
//    string. The value -1 is returned if the character
//    is not found at, or after, the specified start position.
//
//
//  Input:
//
//    character_ptr    A pointer to a value of type 'const CharType'
//
//    start             A value of type 'unsigned int' that
//                      is the  position to start the search.
//
//  Output:
//
//    This method returns a value of type 'int' that is the position
//    of the specified character and the value -1 if the character
//    is not in this at, or after, the start position.
//
//======================================================================

int CharString::Find(const CharType * character_ptr, unsigned int start) const
{
    return static_cast<int>(m_string.find(character_ptr, start));
}

//======================================================================
//  Member Function: CharString::Find
//
//  Abstract:
//
//    This method returns the position of the specified
//    string. The value -1 is returned if the character
//    is not found at, or after, the specified start position.
//
//
//  Input:
//
//    char_string        A pointer to a value of type
//                       'const CharString'
//
//    start             A value of type 'unsigned int' that
//                      is the  position to start the search.
//
//  Output:
//
//    This method returns a value of type 'int' that is the position
//    of the specified character and the value -1 if the character
//    is not in this at, or after, the start position.
//
//======================================================================

int CharString::Find(const CharString & char_string, unsigned int start) const
{
    return static_cast<int>(m_string.find(char_string.CString(), start));
}

//======================================================================
//  Member Function: CharString::ReverseFind
//
//  Abstract:
//
//    This method returns the position of the specified
//    character. The search starts at the end of the string.
//    The value -1 is returned if the character is not found at,
//    or before, the specified start position.
//
//
//  Input:
//
//    character         A value of type 'CharType'
//
//    index              A value of type 'unsigned int' that
//                       is the  position to start the search.
//
//  Output:
//
//    This method returns a value of type 'int' that is the position
//    of the specified character and the value -1 if the character
//    is not in this string before the specified position.
//
//======================================================================

int CharString::ReverseFind(CharType character, unsigned int index) const
{
    return static_cast<int>(m_string.rfind(character, index));
}

//======================================================================
//  Member Function: CharString::ReverseFind
//
//  Abstract:
//
//    This method returns the position of the specified
//    string. The search starts at the end of the string.
//    The value -1 is returned if the character is not found at,
//    or before, the specified start position.
//
//
//  Input:
//
//    character_ptr    A pointer to a value of type 'const CharType'
//
//    index              A value of type 'unsigned int' that
//                       is the  position to start the search.
//
//  Output:
//
//    This method returns a value of type 'int' that is the position
//    of the specified character and the value -1 if the character
//    is not in this string before the specified position.
//
//======================================================================

int CharString::ReverseFind(const CharType * character_ptr, unsigned int index) const
{
    return static_cast<int>(m_string.rfind(character_ptr, index));
}

//======================================================================
//  Member Function: CharString::ReverseFind
//
//  Abstract:
//
//    This method returns the position of the specified
//    string. The search starts at the end of the string.
//    The value -1 is returned if the character is not found at,
//    or before, the specified start position.
//
//
//  Input:
//
//    char_string        A pointer to a value of type
//                       'const CharString'
//
//    index              A value of type 'unsigned int' that
//                       is the  position to start the search.
//
//  Output:
//
//    This method returns a value of type 'int' that is the position
//    of the specified character and the value -1 if the character
//    is not in this string before the specified position.
//
//======================================================================

int CharString::ReverseFind(const CharString & char_string, unsigned int index) const
{
    return static_cast<int>(m_string.rfind(char_string.CString(), index));
}

//======================================================================
//  Member Function: CharString::FindFirstOf
//
//  Abstract:
//
//    This method returns the position of the specified
//    character that matches one of the characters in the passed
//    string. The value -1 is returned if the character
//    is not found at, or after, the specified start position.
//
//
//  Input:
//
//    character         A value of type 'CharType'
//
//    start             A value of type 'unsigned int' that
//                      is the  position to start the search.
//
//  Output:
//
//    This method returns a value of type 'int' that is the position
//    of the specified character found in the set of passed characters,
//    and the value -1 if no character in the passed set of characters
//    is at, or after, the start position.
//
//======================================================================

int CharString::FindFirstOf(CharType character, unsigned int start) const
{
    return static_cast<int>(m_string.find_first_of(start, character));
}

//======================================================================
//  Member Function: CharString::FindFirstOf
//
//  Abstract:
//
//    This method returns the position of the specified
//    character that matches one of the characters in the passed
//    string. The value -1 is returned if the character
//    is not found at, or after, the specified start position.
//
//
//  Input:
//
//    character_ptr    A pointer to a value of type 'const CharType'
//
//    start             A value of type 'unsigned int' that
//                      is the  position to start the search.
//
//  Output:
//
//    This method returns a value of type 'int' that is the position
//    of the specified character found in the set of passed characters,
//    and the value -1 if no character in the passed set of characters
//    is at, or after, the start position.
//
//======================================================================

int CharString::FindFirstOf(const CharType * character_ptr, unsigned int start) const
{
    return static_cast<int>(m_string.find_first_of(character_ptr, start));
}

//======================================================================
//  Member Function: CharString::FindFirstOf
//
//  Abstract:
//
//    This method returns the position of the specified
//    character that matches one of the characters in the passed
//    string. The value -1 is returned if the character
//    is not found at, or after, the specified start position.
//
//
//  Input:
//
//    char_string        A pointer to a value of type
//                       'const CharString'
//
//    start             A value of type 'unsigned int' that
//                      is the  position to start the search.
//
//  Output:
//
//    This method returns a value of type 'int' that is the position
//    of the specified character found in the set of passed characters,
//    and the value -1 if no character in the passed set of characters
//    is at, or after, the start position.
//
//======================================================================

int CharString::FindFirstOf(const CharString & char_string, unsigned int start) const
{
    return static_cast<int>(m_string.find_first_of(char_string.CString(), start));
}

//======================================================================
//  Member Function: CharString::FindLastOf
//
//  Abstract:
//
//    This method returns the position of the specified
//    character that matches one of the characters in the passed
//    string. The search is started at the specified position from
//    the end of the string. The value -1 is returned if the character
//    is not found at, or before, the specified start position.
//
//
//  Input:
//
//    character         A value of type 'CharType'
//
//    start             A value of type 'unsigned int' that
//                      is the  position to start the search.
//
//  Output:
//
//    This method returns a value of type 'int' that is the position
//    of the specified character found in the set of passed characters,
//    and the value -1 if no character in the passed set of characters
//    is at, or before, the start position.
//
//======================================================================

int CharString::FindLastOf(CharType character, unsigned int start) const
{
    return static_cast<int>(m_string.find_last_of(character, start));
}

//======================================================================
//  Member Function: CharString::FindLastOf
//
//  Abstract:
//
//    This method returns the position of the specified
//    character that matches one of the characters in the passed
//    string. The search is started at the specified position from
//    the end of the string. The value -1 is returned if the character
//    is not found at, or before, the specified start position.
//
//
//  Input:
//
//    character_ptr    A pointer to a value of type 'const CharType'
//
//    start             A value of type 'unsigned int' that
//                      is the  position to start the search.
//
//  Output:
//
//    This method returns a value of type 'int' that is the position
//    of the specified character found in the set of passed characters,
//    and the value -1 if no character in the passed set of characters
//    is at, or before, the start position.
//
//======================================================================

int CharString::FindLastOf(const CharType * character_ptr, unsigned int start) const
{
    return static_cast<int>(m_string.find_last_of(character_ptr, start));
}

//======================================================================
//  Member Function: CharString::FindLastOf
//
//  Abstract:
//
//    This method returns the position of the specified
//    character that matches one of the characters in the passed
//    string. The search is started at the specified position from
//    the end of the string. The value -1 is returned if the character
//    is not found at, or before, the specified start position.
//
//
//  Input:
//
//    char_string        A pointer to a value of type
//                       'const CharString'
//
//    start             A value of type 'unsigned int' that
//                      is the  position to start the search.
//
//  Output:
//
//    This method returns a value of type 'int' that is the position
//    of the specified character found in the set of passed characters,
//    and the value -1 if no character in the passed set of characters
//    is at, or before, the start position.
//
//======================================================================

int CharString::FindLastOf(const CharString & char_string, unsigned int start) const
{
    return static_cast<int>(m_string.find_last_of(char_string.CString(), start));
}

//======================================================================
//  Member Function: CharString::operator =
//======================================================================

CharType CharString::operator [](unsigned int index) const
{
    CharType character = 0;

    if (index < Length())
    {
        character = m_string[index];
    }

    return character;
}

//======================================================================
//  Member Function: CharString::operator =
//======================================================================

CharString CharString::operator =(CharType character)
{
    return CharString(m_string.assign(1, character));
}

//======================================================================
//  Member Function: CharString::operator =
//======================================================================

CharString CharString::operator =(const CharType * character_ptr)
{
    return CharString(m_string.assign(character_ptr));
}

//======================================================================
//  Member Function: CharString::operator =
//======================================================================

CharString CharString::operator =(const CharString & char_string)
{
    return CharString(m_string.assign(char_string.CString(),
                                      char_string.Length()));
}

//======================================================================
//  Member Function: CharString::operator +=
//======================================================================

CharString CharString::operator +=(CharType character)
{
    return CharString(m_string.append(1, character));
}

//======================================================================
//  Member Function: CharString::operator +=
//======================================================================

CharString CharString::operator +=(const CharType * character_ptr)
{

    return CharString(m_string.append(character_ptr));
}

//======================================================================
//  Member Function: CharString::operator +=
//======================================================================

CharString CharString::operator +=(const CharString & char_string)
{
    return CharString(m_string.append(char_string.CString()));
}

//======================================================================
//  Global operator: CharString::operator +=
//
//  Abstract:
//
//    This method converts the value in a variable type
//    bool to a string and appends the string to this
//    string object.
//
//
//  Input:
//
//    value_flag        A variable of type bool that is
//                      converted to a string that is then
//                      appended to this string object.
//
//
//  Output:
//
//    This method returns a reference to this string object.
//
//======================================================================

CharString & CharString::operator +=(bool value_flag)
{
    m_string.append(value_flag ? f_true_ptr : f_false_ptr);
    return *this;
}

//======================================================================
//  Global operator: CharString::operator +=
//
//  Abstract:
//
//    This method converts the value in a variable type
//    short to a string and appends the string to this
//    string object.
//
//
//  Input:
//
//    s_value           A variable of type short that is
//                      converted to a string that is then
//                      appended to this string object.
//
//
//  Output:
//
//    This method returns a reference to this string object.
//
//======================================================================

CharString & CharString::operator +=(short s_value)
{
    StringStreamType string_stream;
    string_stream << s_value;
    m_string.append(string_stream.str().c_str());
    return *this;
}

//======================================================================
//  Global operator: CharString::operator +=
//
//  Abstract:
//
//    This method converts the value in a variable type
//    unsigned short to a string and appends the string to
//    this string object.
//
//
//  Input:
//
//    us_value          A variable of type unsigned short that
//                      is converted to a string that is then
//                      appended to this string object.
//
//
//  Output:
//
//    This method returns a reference to this string object.
//
//======================================================================

#ifndef UNICODE
CharString & CharString::operator +=(unsigned short us_value)
{
    StringStreamType string_stream;
    string_stream << us_value;
    m_string.append(string_stream.str().c_str());
    return *this;
}
#endif

//======================================================================
//  Global operator: CharString::operator +=
//
//  Abstract:
//
//    This method converts the value in a variable type
//    int to a string and appends the string to this
//    string object.
//
//
//  Input:
//
//    i_value           A variable of type int that is
//                      converted to a string that is then
//                      appended to this string object.
//
//
//  Output:
//
//    This method returns a reference to this string object.
//
//======================================================================

CharString & CharString::operator +=(int i_value)
{
    StringStreamType string_stream;
    string_stream << i_value;
    m_string.append(string_stream.str().c_str());
    return *this;
}

//======================================================================
//  Global operator: CharString::operator +=
//
//  Abstract:
//
//    This method converts the value in a variable type
//    unsigned int to a string and appends the string to
//    this string object.
//
//
//  Input:
//
//    ui_value          A variable of type unsigned int that
//                      is converted to a string that is then
//                      appended to this string object.
//
//
//  Output:
//
//    This method returns a reference to this string object.
//
//======================================================================

CharString & CharString::operator +=(unsigned int ui_value)
{
    StringStreamType string_stream;
    string_stream << ui_value;
    m_string.append(string_stream.str().c_str());
    return *this;
}

//======================================================================
//  Global operator: CharString::operator +=
//
//  Abstract:
//
//    This method converts the value in a variable type
//    long to a string and appends the string to this
//    string object.
//
//
//  Input:
//
//    l_value           A variable of type long that is
//                      converted to a string that is then
//                      appended to this string object.
//
//
//  Output:
//
//    This method returns a reference to this string object.
//
//======================================================================

CharString & CharString::operator +=(long l_value)
{
    StringStreamType string_stream;
    string_stream << l_value;
    m_string.append(string_stream.str().c_str());
    return *this;
}

//======================================================================
//  Global operator: CharString::operator +=
//
//  Abstract:
//
//    This method converts the value in a variable type
//    unsigned long to a string and appends the string to
//    this string object.
//
//
//  Input:
//
//    ul_value          A variable of type unsigned long that
//                      is converted to a string that is then
//                      appended to this string object.
//
//
//  Output:
//
//    This method returns a reference to this string object.
//
//======================================================================

CharString & CharString::operator +=(unsigned long ul_value)
{
    StringStreamType string_stream;
    string_stream << ul_value;
    m_string.append(string_stream.str().c_str());
    return *this;
}

//======================================================================
//  Global operator: CharString::operator +=
//
//  Abstract:
//
//    This method converts the value in a variable type
//    float to a string and appends the string to this
//    string object.
//
//
//  Input:
//
//    f_value           A variable of type float that is
//                      converted to a string that is then
//                      appended to this string object.
//
//
//  Output:
//
//    This method returns a reference to this string object.
//
//======================================================================

CharString & CharString::operator +=(float f_value)
{
    StringStreamType string_stream;
    string_stream << f_value;
    m_string.append(string_stream.str().c_str());
    return *this;
}

//======================================================================
//  Global operator: CharString::operator +=
//
//  Abstract:
//
//    This method converts the value in a variable type
//    double to a string and appends the string to this
//    string object.
//
//
//  Input:
//
//    d_value           A variable of type double that is
//                      converted to a string that is then
//                      appended to this string object.
//
//
//  Output:
//
//    This method returns a reference to this string object.
//
//======================================================================

CharString & CharString::operator +=(double d_value)
{
    StringStreamType string_stream;
    string_stream << d_value;
    m_string.append(string_stream.str().c_str());
    return *this;
}

//======================================================================
//  Global operator: CharString::operator +=
//
//  Abstract:
//
//    This method converts the value in a variable type
//    long double to a string and appends the string to
//    this string object.
//
//
//  Input:
//
//    ld_value          A variable of type long double that
//                      is converted to a string that is then
//                      appended to this string object.
//
//
//  Output:
//
//    This method returns a reference to this string object.
//
//======================================================================

CharString & CharString::operator +=(long double ld_value)
{
    StringStreamType string_stream;
    string_stream << ld_value;
    m_string.append(string_stream.str().c_str());
    return *this;
}

//======================================================================
//  Global operator: CharString::operator +=
//
//  Abstract:
//
//    This method converts the value in a variable type
//    'void *' to an address string and appends the string
//    to this string object.
//
//
//  Input:
//
//    value_ptr         A variable of type 'void *' that is
//                      converted to an address string that
//                      is then appended to this string object.
//
//
//  Output:
//
//    This method returns a reference to this string object.
//
//======================================================================

CharString & CharString::operator +=(void * value_ptr)
{
    StringStreamType string_stream;
    string_stream << value_ptr;
    m_string.append(string_stream.str().c_str());
    return *this;
}

//======================================================================
//  Global operator +
//======================================================================

CharString operator +(const CharString & left_char_string,
					  const CharString & right_char_string)
{
    CharString result_string(left_char_string);
    result_string += right_char_string;
    return result_string;
}

//======================================================================
//  Global operator +
//======================================================================

CharString operator +(const CharString & char_string,
					  const CharType * character_ptr)
{
    return char_string + CharString(character_ptr);
}

//======================================================================
//  Global operator +
//======================================================================

CharString operator +(const CharType * character_ptr,
					  const CharString & char_string)
{
    return CharString(character_ptr) + char_string;
}

//======================================================================
//  Global operator +
//======================================================================

CharString operator +(const CharString & char_string,
					  CharType character)
{
    return char_string + CharString(character);
}

//======================================================================
//  Global operator +
//======================================================================

CharString operator +(CharType character,
					  const CharString & char_string)
{
    return CharString(character) + char_string;
}

//======================================================================
//  Global operator ==
//======================================================================

bool operator ==(const CharString & left_char_string,
                 const CharString & right_char_string)
{
    return left_char_string.Compare(right_char_string) == 0;
}

//======================================================================
//  Global operator !=
//======================================================================

bool operator !=(const CharString & left_char_string,
                 const CharString & right_char_string)
{
    return left_char_string.Compare(right_char_string) != 0;
}

//======================================================================
//  Global operator <
//======================================================================

bool operator <(const CharString & left_char_string,
                const CharString & right_char_string)
{
    return left_char_string.Compare(right_char_string) < 0;
}

//======================================================================
//  Global operator >
//======================================================================

bool operator >(const CharString & left_char_string,
                const CharString & right_char_string)
{
    return left_char_string.Compare(right_char_string) > 0;
}

//======================================================================
//  Global operator <=
//======================================================================

bool operator <=(const CharString & left_char_string,
                 const CharString & right_char_string)
{
    return left_char_string.Compare(right_char_string) <= 0;
}

//======================================================================
//  Global operator >=
//======================================================================

bool operator >=(const CharString & left_char_string,
                 const CharString & right_char_string)
{
    return left_char_string.Compare(right_char_string) >= 0;
}

#ifndef UNICODE

//======================================================================
//  istream operator for input.
//======================================================================

std::istream & operator >>(std::istream & is, CharString & char_string)
{
    CharType c_array[1024];

    is.getline(c_array, 1024);
    char_string += c_array;

    return is;
}

//======================================================================
//  ostream operator for output.
//======================================================================

std::ostream & operator <<(std::ostream & os, const CharString & char_string)
{
    os << char_string.CString();
    return os;
}

#endif
