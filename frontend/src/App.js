import React, {useState, useEffect} from 'react';
import ProductForm from "./components/ProductForm";
import ProductList from "./components/ProductList";
import {Route, Routes} from 'react-router-dom';
import CategoryForm from "./components/CategoryForm";
import CategoryList from "./components/CategoryList";
import CartList from "./components/CartList";
import OrderList from "./components/OrderList";


const App = () => {
    return (
        <Routes>
            <Route path="/products/add" element={<ProductForm/>}/>
            <Route path="/products" element={<ProductList/>}/>

            <Route path="/categories/add" element={<CategoryForm />} />
            <Route path="/categories" element={<CategoryList />} />

            <Route path="/carts" element={<CartList/>} />
            <Route path="/orders" element={<OrderList/>} />
        </Routes>
    );
};

export default App;
