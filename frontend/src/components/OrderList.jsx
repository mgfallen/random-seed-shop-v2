import React, { useState, useEffect } from 'react';

const OrderList = () => {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        // Fetch order data from the API endpoint
        fetch('http://localhost:8080/api/orders')
            .then(response => response.json())
            .then(data => setOrders(data))
            .catch(error => console.error('Error fetching order data:', error));
    }, []);

    return (
        <div>
            <h2>Your Orders</h2>
            <ul>
                {orders.map(order => (
                    <li key={order.orderId}>
                        Order ID: {order.orderId} - Total Items: {order.totalItems}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default OrderList;
