 const baseUrl = '/products';
        let isCategoryTableVisible = false;
        let isAvailabilityTableVisible = false;

        // Products
        async function fetchProducts() {
            const response = await fetch(baseUrl);
            const products = await response.json();
            const table = document.getElementById('productTable');
            table.innerHTML = '<tr><th>Name</th><th>Amount</th><th>Category</th><th>Availability</th><th>Status</th><th>Actions</th></tr>';
            products.forEach(product => {
                table.innerHTML += `
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.amount}</td>
                        <td>${product.categoryName}</td>
                        <td>${product.availabilityName}</td>
                        <td>${product.status}</td>
                        <td>
                            <button onclick="deleteProduct(${product.id})">Delete</button>
                            <button onclick="editProduct(${product.id}, '${product.name}', ${product.amount})">Edit</button>
                        </td>
                    </tr>`;
            });
            fetchStats();
        }

        async function createProduct() {
            const name = document.getElementById('name').value;
            const amount = document.getElementById('amount').value;
            const categoryId = document.getElementById('categoryId').value;
            const availabilityId = document.getElementById('availabilityId').value;

            const response = await fetch(baseUrl, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ name, amount, categoryId, availabilityId })
            });
            if (response.ok) {
                alert('Product created');
                fetchProducts();
            }
        }

        async function deleteProduct(id) {
            const response = await fetch(`${baseUrl}/${id}`, { method: 'DELETE' });
            if (response.ok) {
                alert('Product deleted');
                fetchProducts();
            }
        }

        function editProduct(id, name, amount) {
            document.getElementById('name').value = name;
            document.getElementById('amount').value = amount;
            document.getElementById('editId').value = id;
        }

        async function updateProduct() {
            const id = document.getElementById('editId').value;
            const name = document.getElementById('name').value;
            const amount = document.getElementById('amount').value;
            const categoryId = document.getElementById('categoryId').value;
            const availabilityId = document.getElementById('availabilityId').value;

            const response = await fetch(baseUrl, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ id, name, amount, categoryId, availabilityId })
            });
            if (response.ok) {
                alert('Product updated');
                fetchProducts();
            }
        }

        // Categories
        async function toggleCategoryTable() {
            const table = document.getElementById('categoryTable');
            if (isCategoryTableVisible) {
                table.style.display = 'none';
                isCategoryTableVisible = false;
            } else {
                await fetchCategories();
                table.style.display = 'table';
                isCategoryTableVisible = true;
            }
        }

        async function fetchCategories() {
            const response = await fetch('/categories');
            const categories = await response.json();
            const table = document.getElementById('categoryTable');
            table.innerHTML = '<tr><th>ID</th><th>Name</th><th>Actions</th></tr>';
            categories.forEach(category => {
                table.innerHTML += `
                    <tr>
                        <td>${category.id}</td>
                        <td>${category.name}</td>
                        <td>
                            <button onclick="deleteCategory(${category.id})">Delete</button>
                            <button onclick="editCategory(${category.id}, '${category.name}')">Edit</button>
                        </td>
                    </tr>`;
            });
            fetchStats();
        }

        async function createCategory() {
            const name = document.getElementById('categoryName').value;

            const response = await fetch('/categories', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ name })
            });
            if (response.ok) {
                alert('Category created');
                fetchCategories();
            }
        }

    async function updateCategory() {
    const id = document.getElementById('categoryIdInput').value;
    const name = document.getElementById('categoryName').value;

    const response = await fetch(`/categories/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name })
    });
    if (response.ok) {
        alert('Category updated');
        fetchCategories();
    }
}


        function editCategory(id, name) {
            document.getElementById('categoryIdInput').value = id;
            document.getElementById('categoryName').value = name;
        }

async function deleteCategory(id) {
    id = Number(id);

    const response = await fetch(`/categories/${id}`, { method: 'DELETE' });
    if (response.ok) {
        alert('Category deleted');
        fetchCategories();
    } else {
        console.error("Failed to delete category");
    }
}


        // Availabilities
        async function toggleAvailabilityTable() {
            const table = document.getElementById('availabilityTable');
            if (isAvailabilityTableVisible) {
                table.style.display = 'none';
                isAvailabilityTableVisible = false;
            } else {
                await fetchAvailabilities();
                table.style.display = 'table';
                isAvailabilityTableVisible = true;
            }
        }

        async function fetchAvailabilities() {
            const response = await fetch('/availabilities');
            const availabilities = await response.json();
            const table = document.getElementById('availabilityTable');
            table.innerHTML = '<tr><th>ID</th><th>Name</th><th>Actions</th></tr>';
            availabilities.forEach(availability => {
                table.innerHTML += `
                    <tr>
                        <td>${availability.id}</td>
                        <td>${availability.name}</td>
                        <td>
                            <button onclick="deleteAvailability(${availability.id})">Delete</button>
                            <button onclick="editAvailability(${availability.id}, '${availability.name}')">Edit</button>
                        </td>
                    </tr>`;
            });
            fetchStats();
        }

        async function createAvailability() {
            const name = document.getElementById('availabilityName').value;

            const response = await fetch('/availabilities', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ name })
            });
            if (response.ok) {
                alert('Availability created');
                fetchAvailabilities();
            }
        }

        async function updateAvailability() {
        const id = document.getElementById('availabilityIdInput').value;
        const name = document.getElementById('availabilityName').value;

        const response = await fetch(`/availabilities/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name })
    });
    if (response.ok) {
        alert('Availability updated');
        fetchAvailabilities();
    }
}

        function editAvailability(id, name) {
            document.getElementById('availabilityIdInput').value = id;
            document.getElementById('availabilityName').value = name;
        }

        async function deleteAvailability(id) {
            const response = await fetch(`/availabilities/${id}`, { method: 'DELETE' });
            if (response.ok) {
                alert('Availability deleted');
                fetchAvailabilities();
            }
        }

        async function populateCategoryFilter() {
            console.log("proverka");
            const response = await fetch('/categories');
            const categories = await response.json();
            const filter = document.getElementById('categoryFilter');

            filter.innerHTML = '<option value="">All Categories</option>';
            categories.forEach(category => {
                filter.innerHTML += `<option value="${category.id}">${category.name}</option>`;
            });
        }

        async function filterProductsByCategory() {
            const selectedCategoryId = document.getElementById('categoryFilter').value;
            const response = await fetch('/products');
            const products = await response.json();
            const filteredProducts = selectedCategoryId
                ? products.filter(product => product.categoryId == selectedCategoryId)
                : products;

            const table = document.getElementById('productTable');
            table.innerHTML = '<tr><th>Name</th><th>Amount</th><th>Category</th><th>Availability</th><th>Status</th><th>Actions</th></tr>';
            filteredProducts.forEach(product => {
                table.innerHTML += `
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.amount}</td>
                        <td>${product.categoryName}</td>
                        <td>${product.availabilityName}</td>
                        <td>${product.status}</td>
                        <td>
                            <button onclick="deleteProduct(${product.id})">Delete</button>
                            <button onclick="editProduct(${product.id}, '${product.name}', ${product.amount})">Edit</button>
                        </td>
                    </tr>`;
            });
        }

       async function fetchStats() {
               const response = await fetch('/stats');
               const stats = await response.json();

               const table = document.getElementById('statsTable');
               table.style.display = 'table';
               table.innerHTML = `
                   <tr>
                       <th>Product quantity</th>
                       <th>Quantity of categories</th>
                       <th>Quantity of availabilities</th>
                   </tr>
                   <tr>
                       <td>${stats.productsAmount}</td>
                       <td>${stats.categoriesAmount}</td>
                       <td>${stats.availabilitiesAmount}</td>
                   </tr>`;

       }



window.addEventListener('load', function() {
    fetchProducts();
    populateCategoryFilter();
    fetchStats();
});