import React, { useState, useEffect } from 'react';

const ProductForm = ({ onAddProduct }) => {
    const [productData, setProductData] = useState({
        id: '',
        name: '',
        description: '',
        price: 0,
        categoryId: '',
        stockQuantity: 0,
        rating: 0,
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setProductData({ ...productData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/products/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(productData),
            });

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const createdProduct = await response.json();
            onAddProduct(createdProduct);

            setProductData({
                id: '',
                name: '',
                description: '',
                price: 0,
                categoryId: '',
                stockQuantity: 0,
                rating: 0,
            });
        } catch (error) {
            console.error('Error adding product:', error.message);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                ID:
                <input
                    type="text"
                    name="id"
                    value={productData.id}
                    onChange={handleInputChange}
                />
            </label>
            <label>
                Название продукта:
                <input
                    type="text"
                    name="name"
                    value={productData.name}
                    onChange={handleInputChange}
                />
            </label>
            <label>
                Описание:
                <textarea
                    name="description"
                    value={productData.description}
                    onChange={handleInputChange}
                />
            </label>
            <label>
                Цена:
                <input
                    type="number"
                    name="price"
                    value={productData.price}
                    onChange={handleInputChange}
                />
            </label>
            <label>
                Категория ID:
                <input
                    type="text"
                    name="categoryId"
                    value={productData.categoryId}
                    onChange={handleInputChange}
                />
            </label>
            <label>
                Количество на складе:
                <input
                    type="number"
                    name="stockQuantity"
                    value={productData.stockQuantity}
                    onChange={handleInputChange}
                />
            </label>
            <label>
                Рейтинг:
                <input
                    type="number"
                    name="rating"
                    value={productData.rating}
                    onChange={handleInputChange}
                />
            </label>
            <button type="submit">Добавить продукт</button>
        </form>
    );
};

export default ProductForm;
