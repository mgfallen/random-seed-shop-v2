import React, { useState } from 'react';

const CategoryForm = () => {
    const [formData, setFormData] = useState({
        name: '',
        description: '',
    });

    const baseUrl = 'http://localhost:8080';

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await fetch(baseUrl + '/api/categories/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                console.log('Category added successfully!');
            } else {
                const errorData = await response.json(); // assuming the server sends detailed error information
                console.error('Failed to add category:', errorData);
            }
        } catch (error) {
            console.error('Error during the POST request:', error);
        }
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevFormData) => ({
            ...prevFormData,
            [name]: value,
        }));
    };

    return (
        <div>
            <h2>Category Form</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Category Name:
                    <input type="text" name="name" value={formData.name} onChange={handleChange} />
                </label>
                <br />
                <label>
                    Description:
                    <textarea name="description" value={formData.description} onChange={handleChange} />
                </label>
                <br />
                <button type="submit">Add Category</button>
            </form>
        </div>
    );
};

export default CategoryForm;
