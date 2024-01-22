import React, { useEffect, useState } from 'react';

const CategoryList = () => {
    const [categories, setCategories] = useState([]);
    const baseUrl = 'http://localhost:8080'

    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const response = await fetch(baseUrl + '/api/categories/all', {
                    headers: {
                        'Content-Type': 'application/json',
                    },
                });

                if (response.ok) {
                    const data = await response.json();
                    setCategories(data);
                } else {
                    const errorData = await response.text(); // Change from json() to text()
                    console.error('Failed to fetch categories:', errorData);
                }
            } catch (error) {
                console.error('Error during the GET request:', error);
            }
        };



        fetchCategories();
    }, []);

    return (
        <div>
            <h2>Category List</h2>
            <ul>
                {categories.map((category) => (
                    <li key={category.id}>
                        <strong>{category.name}</strong>
                        <p>{category.description}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default CategoryList;
