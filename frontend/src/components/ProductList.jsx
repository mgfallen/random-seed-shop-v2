import React from 'react';

const ProductList = ({ products }) => {
    return (
        <div>
            <h2>Список продуктов:</h2>
            <ul>
                {products.map((product) => (
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
            {product.description && <><strong>Описание:</strong> {product.description}<br /></>}
            <strong>Цена:</strong> {product.price}<br />
            <strong>Категория ID:</strong> {product.categoryId}<br />
            {product.stockQuantity !== null && <><strong>Количество на складе:</strong> {product.stockQuantity}<br /></>}
            {product.rating !== null && <><strong>Рейтинг:</strong> {product.rating}<br /></>}
            <hr />
        </li>
    );
};

export default ProductList;
