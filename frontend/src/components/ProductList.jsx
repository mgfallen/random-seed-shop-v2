import React, { useState, useEffect } from 'react';

const ProductList = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        // Fetch products when the component mounts
        fetchProducts();
    }, []);

    const fetchProducts = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/products');
            if (response.ok) {
                const data = await response.json();
                console.log('Fetched data:', data);
                setProducts(data);
            } else {
                console.error('Failed to fetch products');
            }
        } catch (error) {
            console.error('Error during the GET request:', error);
        }
    };


    return (
        <div>
            <h2>Список продуктов:</h2>
            <ul>
                {products && products.map((product) => (
                    <ProductItem key={product.id} product={product} />
                ))}
            </ul>
        </div>
    );
};

const ProductItem = ({ product }) => {
    return (
        <li>
            <strong>ID:</strong> {product.id}<br />
            <strong>Название:</strong> {product.name}<br />
            {product.description && (
                <>
                    <strong>Описание:</strong> {product.description}<br />
                </>
            )}
            <strong>Цена:</strong> {product.price}<br />
            <strong>Категория ID:</strong> {product.categoryId}<br />
            {product.stockQuantity !== null && (
                <>
                    <strong>Количество на складе:</strong> {product.stockQuantity}<br />
                </>
            )}
            {product.rating !== null && (
                <>
                    <strong>Рейтинг:</strong> {product.rating}<br />
                </>
            )}
            <hr />
        </li>
    );
};

export default ProductList;
