<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="initHiddenOrganization">
    <dl>
        <dt>
            <ul>
                <li>
                    <label>Организация:</label>
                    <input type="text" name="organizationName" class="organizationName" value="">
                </li>
                <li>
                    <label>Сайт:</label>
                    <input type="text" name="organizationUrl" class="organizationUrl" value="">
                </li>
            </ul>
            <button class="deleteOrganization">
                Удалить организацию
            </button>
        </dt>
        <button class="addPosition">Добавить позицию</button>
        <hr>
        <input type="hidden" class="positionsEDUCATION" value="0">
    </dl>
</div>