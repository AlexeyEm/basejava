<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<dd id="initHiddenPosition" class="position">
    <div>
        <ul>
            <li>
                <label>Дата начала:</label>
                <input type="date" name="positionBegin" class="positionBegin" size="30" value="3000-01-01">
            </li>
            <li>
                <label>Дата окончания:</label>
                <input type="date" name="positionEnd" class="positionEnd" size="30" value="3000-01-01">
            </li>
            <li>
                <label>Позиция:</label>
                <input type="text" name="positionName" class="positionName" size="30" value="">
            </li>
            <li>
                <label>Описание:</label>
                <input type="text" name="positionDesc" class="positionDesc" size="30" value="">
            </li>
        </ul>
        <button class="deletePosition" data-id="" data-action="deletePosition">Удалить позицию</button>
    </div>
</dd>