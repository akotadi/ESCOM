//**********************************************************************
//  File: SparseArray.h
//  Author: Bill Hallahan
//  Date: March 27, 2000
//
//  Abstract:
//
//    This class provides an implementation for a sparse array.
//
//  Copyright (C) 2000-2013 William Hallahan
//
//  Permission is hereby granted, free of charge, to any person
//  obtaining a copy of this software and associated documentation
//  files (the "Software"), to deal in the Software without restriction,
//  including without limitation the rights to use, copy, modify, merge,
//  publish, distribute, sublicense, and/or sell copies of the Software,
//  and to permit persons to whom the Software is furnished to do so,
//  subject to the following conditions:
//
//  The above copyright notice and this permission notice shall be
//  included in all copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
//  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
//  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
//  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
//  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
//  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
//  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
//  OTHER DEALINGS IN THE SOFTWARE.
//**********************************************************************

#ifndef SPARSEARRAY_H
#define SPARSEARRAY_H

#include <map>

//------------------------------------------------------------------
//  Forward declarations.
//------------------------------------------------------------------

template <typename T_KEY, typename T_ITEM>

class SparseArray;

//------------------------------------------------------------------
//  Class definition for class SparseArrayProxy.
//
//  This is a proxy class for class SparseArray below.
//  See the book "More Effective C++" by Scott Meyers,
//  "Item 30: Proxy classes", page 213 for a detailed description
//  of proxy classes. The proxy class ideally is a nested class
//  inside the class that uses it, but many compilers do not
//  handle nested classes inside of templates correctly, so
//  the proxy is implemented as a stand-alone class.
//
//  In short, the proxy class allows determining whether
//  operator[]() for the SparseArray class is on the left side
//  or the right side of an equals sign in an expression.
//------------------------------------------------------------------

template <typename T_KEY, typename T_ITEM>

class SparseArrayProxy
{
public:

    SparseArrayProxy(SparseArray<T_KEY, T_ITEM> & sparse_array, T_KEY key);

    SparseArrayProxy & operator =(const SparseArrayProxy<T_KEY, T_ITEM> & right_hand_side);

    SparseArrayProxy & operator =(T_ITEM item);

    operator T_ITEM() const;

private:

    SparseArray<T_KEY, T_ITEM> & m_sparse_array;
    T_KEY m_key;
};

//------------------------------------------------------------------
//  Class definition for the sparse map.
//
//  Inheritance was used here, however, it might have been
//  cleaner to use containment for the STL map container and
//  to create methods to allow accessing the map from the
//  proxy class.
//------------------------------------------------------------------

template <typename T_KEY, typename T_ITEM>

class SparseArray : public std::map< T_KEY, T_ITEM, std::less<T_KEY> >
{
public:

    const SparseArrayProxy<T_KEY, T_ITEM> operator [](T_KEY key) const;

    SparseArrayProxy<T_KEY, T_ITEM> operator [](T_KEY key);
};

//----------------------------------------------------------------------
//  Implementation for methods of class SparseArrayProxy.
//
//  Constructor:
//
//    SparseArrayProxy(SparseArray & sparse_array, T_KEY key);
//----------------------------------------------------------------------

template <typename T_KEY, typename T_ITEM>

SparseArrayProxy<T_KEY, T_ITEM>::SparseArrayProxy(SparseArray<T_KEY, T_ITEM> & sparse_array,
                                                  T_KEY key)
  : m_sparse_array(sparse_array),
    m_key(key)
{
}

//----------------------------------------------------------------------
//  SparseArrayProxy & operator =(const SparseArrayProxy & right_hand_side);
//----------------------------------------------------------------------

template <typename T_KEY, typename T_ITEM>

SparseArrayProxy<T_KEY, T_ITEM> &
    SparseArrayProxy<T_KEY, T_ITEM>::operator =(const SparseArrayProxy<T_KEY, T_ITEM> & right_hand_side)
{
    //------------------------------------------------------------------
    //  If the item is the default item then clear the existing item
    //  at this key from the map.
    //------------------------------------------------------------------

    if (T_ITEM(right_hand_side) == T_ITEM())
    {
        typename SparseArray<T_KEY, T_ITEM>::iterator it = m_sparse_array.find(m_key);

        if (it != m_sparse_array.end())
        {
            m_sparse_array.erase(it);
        }
    }
    else
    {
        //--------------------------------------------------------------
        //  Add the item to the map at the specified key.
        //--------------------------------------------------------------

        (static_cast<std::map< T_KEY, T_ITEM, std::less<T_KEY> > &>(m_sparse_array))[m_key] = right_hand_side;
    }

    return *this;
}

//----------------------------------------------------------------------
//  SparseArrayProxy & operator =(T_ITEM & item);
//----------------------------------------------------------------------

template <typename T_KEY, typename T_ITEM>

SparseArrayProxy<T_KEY, T_ITEM> &
    SparseArrayProxy<T_KEY, T_ITEM>::operator =(T_ITEM item)
{
    (static_cast<std::map< T_KEY, T_ITEM, std::less<T_KEY> > &>(m_sparse_array))[m_key] = item;
    return *this;
}

//----------------------------------------------------------------------
//  operator T_ITEM() const;
//----------------------------------------------------------------------

template <typename T_KEY, typename T_ITEM>

SparseArrayProxy<T_KEY, T_ITEM>::operator T_ITEM() const
{
    typename SparseArray<T_KEY, T_ITEM>::iterator it = m_sparse_array.find(m_key);

    if (it != m_sparse_array.end())
    {
        return (*it).second;
    }
    else
    {
        return T_ITEM();
    }
}

//----------------------------------------------------------------------
//  Implementation for methods of class SparseArray.
//
//  const SparseArrayProxy operator [](T_KEY key) const;
//----------------------------------------------------------------------

template <typename T_KEY, typename T_ITEM>

const SparseArrayProxy<T_KEY, T_ITEM> SparseArray<T_KEY, T_ITEM>::operator [](T_KEY key) const
{
    return SparseArrayProxy<T_KEY, T_ITEM>(const_cast< SparseArray<T_KEY, T_ITEM> & >(*this), key);
}

//----------------------------------------------------------------------
//  SparseArrayProxy<T_KEY, T_ITEM> operator [](T_KEY key);
//----------------------------------------------------------------------

template <typename T_KEY, typename T_ITEM>

SparseArrayProxy<T_KEY, T_ITEM> SparseArray<T_KEY, T_ITEM>::operator [](T_KEY key)
{
    return SparseArrayProxy<T_KEY, T_ITEM>(*this, key);
}

#endif
