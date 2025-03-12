const orders = [
    { orderId: '1', orderDate: '2025-02-18', pickupLocation: '地點A', dropoffLocation: '地點B', staff: '張三', status: '已完成', details: '詳細資訊A' },
    { orderId: '2', orderDate: '2025-02-17', pickupLocation: '地點C', dropoffLocation: '地點D', staff: '李四', status: '進行中', details: '詳細資訊B' },
    { orderId: '3', orderDate: '2025-02-19', pickupLocation: '地點E', dropoffLocation: '地點F', staff: '王五', status: '已取消', details: '詳細資訊C' },
    { orderId: '4', orderDate: '2025-02-16', pickupLocation: '地點G', dropoffLocation: '地點H', staff: '趙六', status: '已完成', details: '詳細資訊D' },
    { orderId: '5', orderDate: '2025-02-14', pickupLocation: '地點I', dropoffLocation: '地點J', staff: '周七', status: '進行中', details: '詳細資訊E' },
    { orderId: '6', orderDate: '2025-02-13', pickupLocation: '地點K', dropoffLocation: '地點L', staff: '錢八', status: '已取消', details: '詳細資訊F' },
    { orderId: '7', orderDate: '2025-02-12', pickupLocation: '地點M', dropoffLocation: '地點N', staff: '馬九', status: '已完成', details: '詳細資訊G' },
    { orderId: '8', orderDate: '2025-02-11', pickupLocation: '地點O', dropoffLocation: '地點P', staff: '張十', status: '進行中', details: '詳細資訊H' },
    { orderId: '9', orderDate: '2025-02-10', pickupLocation: '地點Q', dropoffLocation: '地點R', staff: '李十一', status: '已完成', details: '詳細資訊I' },
    { orderId: '10', orderDate: '2025-02-09', pickupLocation: '地點S', dropoffLocation: '地點T', staff: '王十二', status: '已取消', details: '詳細資訊J' },
    { orderId: '11', orderDate: '2025-02-08', pickupLocation: '地點U', dropoffLocation: '地點V', staff: '趙十三', status: '進行中', details: '詳細資訊K' },
    { orderId: '12', orderDate: '2025-02-07', pickupLocation: '地點W', dropoffLocation: '地點X', staff: '周十四', status: '已完成', details: '詳細資訊L' },
];

let currentPage = 1;
let pageSize = 5;
// 設定初始排序為訂單編號
let sortColumn = 'orderId';
// 設定初始排序方向為遞減
let sortDirection = 'desc';

function displayOrders() {
    const startIndex = (currentPage - 1) * pageSize;
    const endIndex = startIndex + pageSize;
    let currentOrders = [...orders];

    // 因為已設定初始排序欄位，這裡直接排序
    currentOrders.sort((a, b) => {
        const aValue = a[sortColumn];
        const bValue = b[sortColumn];

        if (sortColumn === 'orderDate') {
            const dateA = new Date(aValue);
            const dateB = new Date(bValue);
            return sortDirection === 'asc' ? dateA - dateB : dateB - dateA;
        } else if (sortColumn === 'status') {
            const statusOrder = { '已完成': 0, '進行中': 1, '已取消': 2 };
            return sortDirection === 'asc'
                ? statusOrder[aValue] - statusOrder[bValue]
                : statusOrder[bValue] - statusOrder[aValue];
        } else if (sortColumn === 'orderId') {
            const numA = parseInt(aValue);
            const numB = parseInt(bValue);
            return sortDirection === 'asc' ? numA - numB : numB - numA;
        } else {
            return sortDirection === 'asc'
                ? aValue.localeCompare(bValue, 'zh-Hant')
                : bValue.localeCompare(aValue, 'zh-Hant');
        }
    });

    currentOrders = currentOrders.slice(startIndex, endIndex);

    const tableBody = document.querySelector('#orderTable tbody');
    tableBody.innerHTML = '';

    currentOrders.forEach(order => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${order.orderId}</td>
            <td>${order.orderDate}</td>
            <td>${order.pickupLocation}</td>
            <td>${order.dropoffLocation}</td>
            <td>${order.staff}</td>
            <td>${order.status}</td>
            <td>
                <button class="details-btn" onclick="showDetails('${order.orderId}')">查看<i class="icofont-paw"></i></button>
            </td>
        `;
        tableBody.appendChild(row);
    });

    document.getElementById('pageNumber').textContent = `第 ${currentPage} 頁`;
    document.getElementById('prevPage').disabled = currentPage === 1;
    document.getElementById('nextPage').disabled = currentPage * pageSize >= orders.length;
}

function sortOrders(column) {
    if (sortColumn === column) {
        sortDirection = sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
        sortColumn = column;
        sortDirection = 'asc';
    }
    displayOrders();
}

function showDetails(orderId) {
    window.location.href = `./order.html?orderId=${orderId}`;
}

function changePage(direction) {
    currentPage += direction;
    displayOrders();
}

function updatePageSize() {
    pageSize = parseInt(document.getElementById('pageSize').value);
    currentPage = 1;
    displayOrders();
}

// 初始化顯示
displayOrders();