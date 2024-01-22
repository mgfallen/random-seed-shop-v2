import React, { useState, useEffect } from 'react';

const CartList = () => {
    const [cartItems, setCartItems] = useState([]);

    useEffect(() => {
        // Fetch cart data from the API endpoint
        fetch('http://localhost:8080/api/cart')
            .then(response => response.json())
            .then(data => setCartItems(data))
            .catch(error => console.error('Error fetching cart data:', error));
    }, []);

    return (
        <div>
            <h2>Your Cart</h2>
            <ul>
                {cartItems.map(item => (
                    <li key={item.productId}>
                        {item.productName} - Quantity: {item.quantity}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default CartList;
