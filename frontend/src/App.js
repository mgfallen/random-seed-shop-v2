import React, {useState, useEffect} from 'react';
import ProductForm from "./components/ProductForm";
import ProductList from "./components/ProductList";
import {Route, Routes} from 'react-router-dom';
import CategoryForm from "./components/CategoryForm";
import CategoryList from "./components/CategoryList";


const App = () => {
    return (
        <Routes>
            <Route path="/products/add" element={<ProductForm/>}/>
            <Route path="/products" element={<ProductList/>}/>

            <Route path="/categories/add" element={<CategoryForm />} />
            <Route path="/categories" element={<CategoryList />} />
        </Routes>
    );
};

export default App;
