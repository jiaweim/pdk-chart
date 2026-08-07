# 离散颜色

## 简介

离散色系相关概念：

- 颜色序列（color sequence）：用于映射离散数据值的颜色列表。和连续 colorscale 不同，使用 color-sequence 不会进行差值运算，每种颜色直接调用。color-sequence 的默认值取决于当前主题的 `DrawingSupplier` 中的 `paintSequence` 属性。